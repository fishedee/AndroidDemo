package com.example.myapplication.scope

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import java.lang.RuntimeException
import kotlin.system.measureTimeMillis

fun channelAsFlowTest1(){
    //ChannelAsFlow，recieveAsFlow，可以将flow的输入从闭环输入，改为开环输入
    //当Channel触发的时候，触发flow的emit，并触发flow的collect
    //receiveAsFlow转换为flow的时候，多个collect同时接收事件，每个事件仅被一个collect处理
    runBlocking {
        var chan = Channel<Int>()
        val receiveFlow = chan.receiveAsFlow()
        //没有collect的发送，发送会堵塞
        //chan.send(100)
        launch{
            for( i in (1..5)){
                chan.send(i)
            }
            //显式调用close，才能结束所有的collect
            chan.close()
        }
        launch{
            receiveFlow.collect { v->
                println("collect#1 ${v}")
            }
        }
        launch{
            receiveFlow.collect { v->
                println("collect#2 ${v}")
            }
        }
    }
    /*
    输出如下：
    collect#1 1
    collect#1 2
    collect#1 4
    collect#2 3
    collect#1 5
     */
}

fun channelAsFlowTest1_2(){
    //consumeAsFlow，与receiveAsFlow的区别在于，consumeAsFlow只支持一个collect来接收数据
    runBlocking {
        var chan = Channel<Int>()
        val receiveFlow = chan.consumeAsFlow()
        //没有collect的发送，发送会堵塞
        //chan.send(123)
        launch{
            for( i in (1..5)){
                chan.send(i)
            }
            chan.close()
        }
        launch{
            receiveFlow.collect { v->
                println("collect#1 ${v}")
            }
        }
        /*
        //第二次collect会抛出异常
        launch{
            receiveFlow.collect { v->
                println("collect#2 ${v}")
            }
        }
         */
    }
    /*
    输出如下：
    collect#1 1
    collect#1 2
    collect#1 3
    collect#1 4
    collect#1 5
     */
}

fun channelAsFlowTest2(){
    runBlocking {
        val time = measureTimeMillis {
            var chan = Channel<Int>()
            val flow = chan.receiveAsFlow()
            coroutineScope {
                launch{
                    for( i in (1..3)){
                        delay(100)
                        chan.send(i)
                    }
                    chan.close()
                }
                launch {
                    flow.collect { v->
                        delay(200)
                        println(v)
                    }
                }
            }
        }
        //总共用了200ms*3 = 600ms+100ms= 700ms，注意与channelTest2的不同
        //因为collect的触发，需要等待chann的第一次send的触发
        println("all time ${time} ms")
    }
    /*
    输出如下：
    1
    2
    3
    all time 736 ms
     */
}

fun channelAsFlowTest3(){
    runBlocking {
        val chann = Channel<Int>()
        val flow = chann.receiveAsFlow()
        //两者独立运行，send的崩溃，不影响collect的异常
        supervisorScope {
            launch{
                for(i in (1..3)){
                    chann.send(i)
                    if( i == 2 ){
                        throw RuntimeException("ee")
                    }
                }
            }
            val job = launch {
                flow.collect { v->
                    println(v)
                }
                println("finish success")
            }
            delay(300)
            job.cancel()
        }
    }
    /*
    输出如下：
    1
    Exception in thread "main" java.lang.RuntimeException: ee
        at com.example.myapplication.scope.ChannelAsFlowKt$channelAsFlowTest3$1$1$1.invokeSuspend(ChannelAsFlow.kt:126)
        at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
        at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:106)
        at kotlinx.coroutines.EventLoopImplBase.processNextEvent(EventLoop.common.kt:274)
        at kotlinx.coroutines.BlockingCoroutine.joinBlocking(Builders.kt:85)
        at kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking(Builders.kt:59)
        at kotlinx.coroutines.BuildersKt.runBlocking(Unknown Source)
        at kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking$default(Builders.kt:38)
        at kotlinx.coroutines.BuildersKt.runBlocking$default(Unknown Source)
        at com.example.myapplication.scope.ChannelAsFlowKt.channelAsFlowTest3(ChannelAsFlow.kt:117)
        at com.example.myapplication.scope.ChannelAsFlowKt.ChannelAsFlowTest_Go(ChannelAsFlow.kt:169)
        at com.example.myapplication.MainTestKt.main(MainTest.kt:8)
        at com.example.myapplication.MainTestKt.main(MainTest.kt)
    2
     */
}

fun channelAsFlowTest4(){
    //两者独立运行，send的cancel，不影响collect的cancel
    runBlocking {
        val chann = Channel<Int>()
        val flow = chann.receiveAsFlow()
        val job = launch{
            for(i in (1..3)){
                delay(100)
                chann.send(i)
            }
        }
        val job2 = launch {
            flow.collect { v->
                println(v)
            }
        }
        delay(210)
        job.cancel()
        delay(220)
        job2.cancel()
    }
    /*
    输出如下：
    1
    2
     */
}

fun ChannelAsFlowTest_Go(){
    //channelAsFlowTest1()
    channelAsFlowTest1_2()
    //channelAsFlowTest2()
    //channelAsFlowTest3()
    //channelAsFlowTest4()
}
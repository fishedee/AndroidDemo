package com.example.myapplication.scope

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import kotlin.system.measureTimeMillis

fun channelTest1(){
    //Channel的区别是开放输入，开放输出
    //输入是允许在不同时机，不同闭包中产生的，输出是主动触发的。
    //发送和接收的地方可以在多个闭包里面，不同的协程里面都无所谓。
    //唯一的限制是，发送和接收都在线的时候，通信才会出现。任意一方缺失的时候，都会堵塞
    runBlocking {
        var chan = Channel<Int>()
        launch{
            for( i in (1..5)){
                chan.send(i)
            }
        }
        launch{
            for( i in (1..5)){
                chan.send(i+10)
            }
        }
        launch {
            repeat(2){
                repeat(5){
                    val data = chan.receive()
                    println(data)
                }
            }
        }
    }
}

fun channelTest2(){
    runBlocking {
        val time = measureTimeMillis {
            var chan = Channel<Int>()
            coroutineScope {
                launch{
                    for( i in (1..3)){
                        delay(100)
                        chan.send(i)
                    }
                }
                launch {
                    for( i in (1..3)){
                        delay(200)
                        val data = chan.receive()
                        println(data)
                    }
                }
            }
        }
        //总共用了200ms*3 = 600ms，注意与flowTest2的不同。
        //Flow，emit的返回需要等待collect的完成
        //Channel，send的返回只需要对方调用了receive就可以了，不需要等待对方完成receive的整个任务
        println("all time ${time} ms")
    }
}

fun channelTest3(){
    //放在开放输入，和开放输出的时候是
    //输入端的异常，不会让channel自动关闭，也不会让接收端知道异常而退出
    //Channel就像无法感知到异常发生了一样
    //两者独立运行
    runBlocking {
        val chann = Channel<Int>()
        supervisorScope {
            launch{
                for(i in (1..3)){
                    chann.send(i)
                    if( i == 2 ){
                        throw RuntimeException("ee")
                    }
                }
            }
            launch {
                    for (i in (1..3)) {
                        val v = chann.receive()
                        println(v)
                    }
                    println("finish success")
            }
            delay(300)
            //推送最后一个数据，通知接收端关闭
            chann.send(3)
        }
    }
}

fun channelTest4(){
    //放在开放输入，和开放输出的时候是
    //对发射端的cancel，并不会触发接收端的cancel
    //两者独立运行
    runBlocking {
        val chann = Channel<Int>()
        val job = launch{
            for(i in (1..3)){
                delay(100)
                chann.send(i)
            }
        }
        launch {
            for (i in (1..3)) {
                val v = chann.receive()
                println(v)
            }
            println("finish success")
        }
        delay(210)
        job.cancel()
        //推送最后一个数据，通知接收端关闭
        chann.send(3)
    }
}


fun ChannelTest_Go() {
    //channelTest1()
    //channelTest2()
    //channelTest3()
    channelTest4()
}
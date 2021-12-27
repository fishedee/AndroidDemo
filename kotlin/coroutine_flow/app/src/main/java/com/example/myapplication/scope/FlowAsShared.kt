package com.example.myapplication.scope

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import java.lang.RuntimeException
import kotlin.system.measureTimeMillis

fun flowAsSharedTest1(){
    //flow.shareIn，和stateIn的特点在于，将单被动输出，改为多被动输出
    //多个闭合输出之间，当flow发送某个数据的时候，所有collect都能收到同样的数据（订阅模式）
    //如果某个collect是延后才参与到flow的时候，它只会收到之后的数据（取决于relay参数）
    //如果某个collect是中途就退出到flow的时候，它就会丢掉部分数据，也不会堵塞flow的emit
    //这种模式下的collect端，不会随着flow的关闭而自动关闭
    runBlocking {
        val globalJob = launch {
            val myFlow = flow{
                for( i in (1..5)){
                    delay(100)
                    emit(i)
                }
            }
            //Lazily的方式是，当第一个collect的时候，才去触发flow的启动
            val sharedFlow = myFlow.shareIn(GlobalScope,SharingStarted.Lazily,0)
            launch{
                sharedFlow.collect { v->
                    println("collect#1 ${v}")
                }
            }

            launch{
                delay(200)
                //延迟启动
                sharedFlow.collect { v->
                    println("shared#2 ${v}")
                }
            }
            val job = launch{
                //提前结束
                sharedFlow.collect { v->
                    println("shared#3 ${v}")
                }
            }
            delay(300)
            job.cancel()
        }

        delay(700)
        globalJob.cancel()
    }
    /*
    输出如下：
    collect#1 1
    shared#3 1
    collect#1 2
    shared#3 2
    shared#2 2
    collect#1 3
    shared#2 3
    collect#1 4
    shared#2 4
    collect#1 5
    shared#2 5

    Process finished with exit code 0
     */
}

fun flowAsSharedTest1_2(){
    runBlocking {
        val myFlow = flow{
            for( i in (1..5)){
                delay(100)
                emit(i)
            }
        }
        //stateIn与sharedIn很相似，订阅模式，多闭合输出，可以延后进入，可以中途退出
        //stateIn的唯一区别是，它记录state的初始值，和最后一个值
        //对于首次进入的collect，它会先推送initialValue，然后推送flow的emit值
        //对于延后进入的collect，它会先推送flow的最后一个值，然后推送flow后续的emit值
        val stateFlow = myFlow.stateIn(GlobalScope,SharingStarted.Lazily,100)
        launch{
            stateFlow.collect { v->
                println("collect#1 ${v}")
            }
        }

        launch{
            delay(200)
            //延迟启动
            stateFlow.collect { v->
                println("shared#2 ${v}")
            }
        }
        val job = launch{
            //提前结束
            stateFlow.collect { v->
                println("shared#3 ${v}")
            }
        }
        delay(100)
        job.cancel()
    }
    /*
    输出如下：
    collect#1 100
    shared#3 100
    collect#1 1
    shared#2 1
    collect#1 2
    shared#2 2
    collect#1 3
    shared#2 3
    collect#1 4
    shared#2 4
    collect#1 5
    shared#2 5
     */
}

fun flowAsSharedTest2(){
    runBlocking {
        val time = measureTimeMillis {
            val myFlow = flow{
                for( i in (1..3)){
                    delay(100)
                    emit(i)
                }
            }
            val sharedFlow = myFlow.shareIn(GlobalScope, SharingStarted.Lazily,0)
            coroutineScope {
                val channel = Channel<Int>()
                val job = launch{
                    var i = 0
                    sharedFlow.collect {v->
                        delay(200)
                        println(v)
                        i++
                        if( i == 3 ){
                            channel.send(0)
                        }
                    }
                }
                channel.receive()
                job.cancel()
            }
        }
        //由于collect不会自动退出，所以要辅助cancel掉
        //总共用了200ms*3 = 600ms+100ms= 700ms，注意与channelTest2的不同
        println("all time ${time} ms")
    }
    /*
    输出如下：
    1
    2
    3
    all time 757 ms
     */
}

fun flowAsSharedTest3(){
    //Flow的发射在GlobalScope作用域，两者独立运行，它的崩溃与collect作用域无关
    runBlocking {
        val myFlow = flow{
            for( i in (1..3)){
                emit(i)
                if( i == 2 ){
                    throw RuntimeException("ee")
                }
            }
        }
        val sharedFlow = myFlow.shareIn(GlobalScope, SharingStarted.Lazily,0)
        launch{
            var i = 0
            sharedFlow.collect {v->
                println(v)
                i++
            }
        }
    }
    /*
    输出如下：
    1
    2
    Exception in thread "DefaultDispatcher-worker-1" java.lang.RuntimeException: ee
     */
}

fun flowAsSharedTest4(){
    //Flow的发射在CoroutineScope作用域，两者独立运行，它的cancel与collect作用域无关
    runBlocking {
        val job = Job()
        val myScope = CoroutineScope(job)
        val myFlow = flow{
            for( i in (1..3)){
                emit(i)
            }
        }
        val sharedFlow = myFlow.shareIn(myScope, SharingStarted.Lazily,0)
        launch{
            try {
                var i = 0
                sharedFlow.collect { v ->
                    println(v)
                    i++
                }
            }catch(e:Exception){
                e.printStackTrace()
            }
        }
        launch {
            job.cancel()
        }
    }
    /*
    输出如下
    1
     */
}

fun FlowAsSharedTest_Go() {
    //flowAsSharedTest1()
    //flowAsSharedTest1_2()
    //flowAsSharedTest2()
    //flowAsSharedTest3()
    flowAsSharedTest4()
}
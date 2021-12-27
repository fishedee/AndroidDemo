package com.example.myapplication.scope

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun createFlow1(){
    runBlocking {
        //使用函数的方式创建流
        val myFlow = flow { // 流构建器
            for (i in 1..3) {
                delay(100) // 假装我们在这里做了一些有用的事情
                emit(i) // 发送下一个值
            }
        }
        launch {
            myFlow.collect { v->
                println(v)
            }
        }
    }
    /*
    输出如下：
    1
    2
    3
     */
}

fun createFlow2(){
    runBlocking {
        //使用asFlow创建流
        val myFlow = (1..3).asFlow()
        launch {
            myFlow.collect { v->
                println(v)
            }
        }
    }
}

fun myCallbackFlow() = callbackFlow<Int> {
    println("in thread")
    val thread = Thread{

        for(i in (1..3)){
            Thread.sleep(100)
            //往外吐出数据
            trySendBlocking(i)
                .onFailure {
                    println("oh fail")
                }

        }
        close()
    }
    thread.start()
    //awaitClose这一句会堵塞
    awaitClose {
        //收到flow的cancel，或者close消息
        thread.interrupt()
    }
    println("out thread")
}

fun createFlow3(){
    runBlocking {
        //使用callbackFlow创建流
        val myFlow = myCallbackFlow()
        launch {
            myFlow.collect { v->
                println(v)
            }
        }
    }
    /*
    输出如下：
    in thread
    1
    2
    3
     */
}

fun CreateFlowTest_Go() {
    //createFlow1()
    //createFlow2()
    createFlow3()
}
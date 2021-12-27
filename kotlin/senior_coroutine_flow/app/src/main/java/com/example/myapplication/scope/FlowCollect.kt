package com.example.myapplication.scope

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun flowCollect(){
    runBlocking {
        //使用collect收集，阻塞，并等待结束
        val myFlow = (1..3).asFlow()
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

fun flowLaunch(){
    runBlocking {
        //使用launchIn收集，非阻塞，异步
        val myFlow = (1..3).asFlow()
        val job = myFlow
            .onEach { v->
                delay(100)
                println(v)
            }
            .launchIn(this)
        println("start")
        job.join()
        println("end")
    }
    /*
    输出如下：
    start
    1
    2
    3
    end
     */
}


fun flowToList(){
    runBlocking {
        //使用asFlow创建流
        val myFlow = (1..3).asFlow()
        launch {
            val data = myFlow.toList()
            println(data)
        }
    }
    /*
    输出如下
    [1, 2, 3]
     */
}

fun FlowCollectTest_Go() {
    //flowCollect()
    //flowLaunch()
    flowToList()
}
package com.example.myapplication.scope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

fun flowOnTest(){
    runBlocking {
        val myFlow = flow{
            for( i in (1..3)){
                Thread.sleep(100)
                emit(i)
            }
        }
            //flowOn之前的运行在Default的协程
            .flowOn(Dispatchers.Default)
            //flowOn之后的还是运行在runBlocking{}的协程里面
            .collect { v->
                println(v)
            }
    }
    /*
    输出如下:
    1
    2
    3
     */
}
fun FlowContextTest_Go() {
    flowOnTest()
}
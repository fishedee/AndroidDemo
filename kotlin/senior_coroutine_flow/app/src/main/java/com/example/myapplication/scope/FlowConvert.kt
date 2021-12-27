package com.example.myapplication.scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun flowMapAndFilter(){
    runBlocking {
        val myFlow = (1..10).asFlow()
        myFlow.map{v->v+10}
            .filter { v-> v%2 ==0 }
            .collect { v->
                println(v)
            }
    }
    /*
    输出如下：
    12
    14
    16
    18
    20
     */
}

fun flowTransform(){
    runBlocking {
        val myFlow = (1..3).asFlow()
        myFlow.transform{v->
                emit("first ${v+10}")
                delay(100)
                emit("second ${v+20}")
            }
            .collect { v->
                println(v)
            }
    }
    /*
    输出如下：
    first 11
    second 21
    first 12
    second 22
    first 13
    second 23
     */
}

fun flowFlatMerge(){
    runBlocking {
        val myFlow = (1..3).asFlow()
        myFlow.flatMapMerge{v->
                flow<String>{
                    emit("first ${v+10}")
                    delay(100)
                    emit("second ${v+20}")
                }
            }
            .collect { v->
                println(v)
            }
    }
    /*
    输出如下：
    first 11
    first 12
    first 13
    second 21
    second 22
    second 23
     */
}

fun FlowConvertTest_Go(){
    //flowMapAndFilter()
    //flowTransform()
    flowFlatMerge()
}
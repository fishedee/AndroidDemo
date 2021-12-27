package com.example.myapplication.scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException
import kotlin.system.measureTimeMillis

fun flowTest1(){
    //闭合的输入，产生的闭合的输出
    //输入是一个闭包中产生的，输出是被动触发的。
    runBlocking {
        flow{
            for(i in (1..10)){
                emit(i)
            }
        }.collect { v->
            println(v)
        }
    }
}

fun flowTest2(){
    runBlocking {
        var time = measureTimeMillis{
            flow{
                for(i in (1..3)){
                    delay(100)
                    emit(i)
                }
            }.collect { v->
                delay(200)
                println(v)
            }
        }
        //每个数据是100ms+200ms=300ms，共需要900ms的时间
        println("time ${time} ms")
    }
}

fun flowTest3(){
    //放在闭合输入，和输出的好处是
    //当输入或者输出发生异常的时候，发射端和收集端都会自动退出，并清理相关资源
    runBlocking {
        try{
            flow{
                for(i in (1..3)){
                    emit(i)
                    if( i == 2 ){
                        throw RuntimeException("ee")
                    }
                }

            }.collect { v->
                delay(200)
                println(v)
            }
        }catch(e:Exception){
            e.printStackTrace()
        }
    }
}

fun flowTest4(){
    //放在闭合输入，和输出的另外一个好处是：
    //受到结构化并发的控制，能附带将整个发射端，和收集端都cancel掉
    runBlocking {
        val job = launch {
            flow {
                for (i in (1..3)) {
                    emit(i)
                    delay(100)
                }
            }.collect { v ->
                delay(200)
                println(v)
            }
        }
        delay(400)
        job.cancel()
    }
}


fun FlowTest_Go(){
    //flowTest1()
    //flowTest2()
    //flowTest3()
    flowTest4()
}
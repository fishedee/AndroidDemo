package com.example.myapplication.scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun flowTestLongCollect(){
    //背压问题，发射与收集端的速率不一致，导致发射方需要停下来等待收集端处理完毕以后才能发射下一个
    runBlocking {
        val time = measureTimeMillis {
            flow{
                for( i in (0..3)){
                    delay(100)
                    emit(i)
                }
            }.collect {
                delay(200)
                println(it)
            }
        }
        //每个元素需要300ms，共4个元素，需要1200ms
        println("all Time ${time}")
    }
    /*
    输出如下：
    0
    1
    2
    3
    all Time 1245
     */
}

fun flowBuffer(){
    //从发射方解决背压问题，将发射方的数据缓存起来，然后马上发射下一个
    runBlocking {
        val time = measureTimeMillis {
            flow{
                for( i in (0..3)){
                    delay(100)
                    emit(i)
                }
            }.buffer()
                .collect {
                delay(200)
                println(it)
            }
        }
        //每个元素collect端需要200ms，共4个元素，需要800ms
        //另外首次发射需要100ms，所以共900ms
        println("all Time ${time}")
    }
    /*
    输出如下：
    0
    1
    2
    3
    all Time 967
     */
}

fun flowConflate(){
    //从发射方解决背压问题，将发射方数据尝试发射，如果收集端阻塞，就用最新的数据覆盖整个缓存区的数据
    //从而让收集端只处理最新的数据，中间的数据跳过去了
    runBlocking {
        val time = measureTimeMillis {
            flow{
                for( i in (0..3)){
                    delay(100)
                    emit(i)
                }
            }.conflate()
                .collect {
                    delay(200)
                    println(it)
                }
        }
        //只有3个元素实际被处理了，每个处理时间为200ms，共600ms
        //加入首次发射的100ms，共700ms
        println("all Time ${time}")
    }
    /*
    输出如下：
    0
    1
    3
    all Time 761
     */
}

fun flowLatest() {
    //collectLatest是从收集方解决问题，如果新数据到来了，但是收集方还没处理完上次的数据
    // 就直接cancel收集方，让收集方马上立即处理最新数据
    //注意collectLatest与conflate的不同
    runBlocking {
        val time = measureTimeMillis {
            flow{
                for( i in (0..3)){
                    delay(100)
                    emit(i)
                }
            }
                .collectLatest {
                    delay(200)
                    println(it)
                }
        }
        //发射元素为4个，每个100ms，共400ms
        //只处理了最后一个元素，处理时间为200ms，共600ms
        println("all Time ${time}")
    }
    /*
    输出如下：
    3
    all Time 657
     */
}

fun FlowBackPressureTest_Go() {
    //flowTestLongCollect()
    //flowBuffer()
    //flowConflate()
    flowLatest()
}
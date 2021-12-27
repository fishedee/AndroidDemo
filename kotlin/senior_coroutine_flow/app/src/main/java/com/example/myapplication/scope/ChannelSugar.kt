package com.example.myapplication.scope

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun channelBasic(){
    runBlocking {
        val channel = Channel<Int>()
        launch {
            for( i in (1..3)){
                channel.send(i)
            }
            channel.close()
        }
        launch {
            while( true){
                val receiveResult = channel.receiveCatching()
                if( receiveResult.isClosed ){
                    break
                }
                println(receiveResult.getOrThrow())
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

fun channelForSugar(){
    runBlocking {
        val channel = Channel<Int>()
        launch {
            for( i in (1..3)){
                channel.send(i+1)
            }
            channel.close()
        }
        launch {
            for( data in channel ){
                println(data)
            }
        }
    }
    /*
    输出如下：
    2
    3
    4
     */
}

fun channelProduceProduce(){
    runBlocking {
        val channel = produce {
            for( i in (1..3)){
                send(i+2)
            }
        }
        launch {
            for( data in channel ){
                println(data)
            }
        }
    }
    /*
    输出如下：
    3
    4
    5
     */
}

fun ChannelSugarTest_Go() {
    //channelBasic()
    //channelForSugar()
    channelProduceProduce()
}
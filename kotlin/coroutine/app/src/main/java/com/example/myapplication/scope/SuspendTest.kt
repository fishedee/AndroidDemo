package com.example.myapplication.scope

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun go1(){
    //这种方法会堵塞协程
    Thread.sleep(10)
}

//挂起方法的定义是在方法中加入suspend关键字
//对于js来说，相当于一个async方法
suspend fun go2(){
    //这种方法不堵塞协程
    delay(10)
}

class MyTimeout{
    fun wait(timeout:Long,callback:(target:Int)->Unit){
        Thread{
            Thread.sleep(timeout)
            callback(100)
        }.start()
    }
}

//suspendCoroutine是一个特殊的方法，可以将协程暂时切换到出去
//当数据有返回的时候，我们才触发continuation.resume来触发数据
//这是一个协程切换到普通线程，普通线程再切换回来的重要方法
//对于js来说，这个方法相当于用Promise包装一个async函数
suspend fun suspendCoroutine_1():Int{
    return suspendCoroutine{ continuation->
        MyTimeout().wait(100) { result ->
            continuation.resume(result)
        }
    }
}

suspend fun suspendCoroutine_2():Int{
    return suspendCoroutine{ continuation->
        MyTimeout().wait(100) { result ->
            continuation.resumeWithException(Exception("exception1"))
        }
    }
}


suspend fun suspendCoroutine_3():Int{
    return suspendCoroutine{ continuation->
        MyTimeout().wait(100) { result ->
            val returnResult:Result<Int> = Result.success(102)
            var returnException:Result<Int> = Result.failure(Exception("exception2"))
            if( (1..2).random() == 1 ){
                continuation.resumeWith(returnResult)
            }else{
                continuation.resumeWith(returnException)
            }
        }
    }
}

fun testBasic(){
    runBlocking {
        println("1")
        delay(100)
        println("2")
        //在协程里面，我们可以调用普通方法
        go1()
        println("3")

        //我们也可以调用特定的挂起方法
        //调用挂起方法的意义在于，我们可以高效的使用协程。
        //调用普通方法，遇到堵塞的时候不会让出时间片，但是挂起方法遇到堵塞的时候会自动让出时间片给其他的协程
        //注意，这里与js不同。js调用async方法以后，可以await，也可以不await。
        // 但是在Kotlin的协程里面，必须await，这种调用方式是隐式的，这种方法其实避免隐晦的bug，但是带来是不灵活的代价
        go2()
        println("4")

        val a = suspendCoroutine_1()
        println("a is ${a}")
        println("5")

        try{
            val b = suspendCoroutine_2()
        }catch(e:Exception){
            e.printStackTrace()
            println("6")
        }

        try{
            val c = suspendCoroutine_3()
            println("7 c is ${c}")
        }catch(e:Exception){
            e.printStackTrace()
            println("7")
        }

    }
}

fun Suspend_Test(){
    testBasic()
}
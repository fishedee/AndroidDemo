package com.example.myapplication.cancel

import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class MyTimeout{
    fun wait(timeout:Long,callback:(target:Int)->Unit):Thread{
        val thread = Thread{
            try{
                Thread.sleep(timeout)
                callback(100)
            }catch(e:InterruptedException){
                println("catch thread interrupt")
            }
        }
        thread.start()
        return thread
    }
}

suspend fun suspendCoroutine_1():Int{
    //注意改为了suspendCancellableCoroutine，而不是suspendCoroutine
    return suspendCancellableCoroutine{ continuation->
        val thread = MyTimeout().wait(100) { result ->
            continuation.resume(result)
        }
        //关键是这一句，invokeOnCancellation的意义是，当收到协程cancel的时候，得到额外的通知
        //这样可以快速停止普通作用域的操作，例如及时停止线程池中对网络请求的发送（OkHttp），释放回调避免对View的内存占用（AutoDispose），
        continuation.invokeOnCancellation {
            //通知线程interrupt
            println("thread cancel!!!")
            thread.interrupt()
        }

    }
}


fun CancelSuspend(){
    runBlocking {
        var a = GlobalScope.launch {
            println("1")
            launch {
                println("3")
                suspendCoroutine_1()
                //不执行
                println("4")
            }
            launch {
                println("5")
                delay(100)
                //不执行
                println("6")
            }
            delay(100)
            //不执行
            println("2")
        }
        delay(30)
        a.cancel()
        /*
        1
        3
        5
        thread cancel!!!
        catch interrupt
         */
    }
}

fun CancelSuspendTest_Go(){
    CancelSuspend()
}
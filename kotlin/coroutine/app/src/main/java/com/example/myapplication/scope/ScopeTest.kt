package com.example.myapplication.scope

import kotlinx.coroutines.*
import kotlin.time.Duration

fun runBlockingTest(){
    println("1")
    //runBlocking是全局方法，启动协程，并且会堵塞当前线程，直至协程作用域的结束
    runBlocking {
        println("2")
        delay(100)
        println("3")
    }
    println("4")

    //输出为1,2,3,4
}

fun globalScopeLaunchTest(){
    println("1")
    //GlobalScope是一个单例，也是一个协程作用域，launch是它的方法
    //GlobalScope启动后不堵塞当前线程，并且返回一个job方法，可以用来检查协程作用域是否已经结束
    val job = GlobalScope.launch {
        println("2")
        delay(100)
        println("3")
    }
    println("4")
    Thread.sleep(200)
    //等待协程作用域的结束，join方法
    //job.join()
    println("5")

    //输出为1,4,2,3,5
}

fun CoroutineScopeLaunchTest(){
    println("1")
    //先创建一个Job
    var job = Job()
    //CoroutineScope是一个类，也是一个协程作用域，launch是它的方法
    //CoroutineScope与GlobalScope的区别在于，它绑定到一个job上来，而不是返回一个新的job
    //因此可以将多个CoroutineScope绑定到同一个job上，我们可以同时用单个job等待或者检查多个CoroutineScope
    CoroutineScope(job).launch{
        println("2")
        delay(100)
        println("3")
    }
    println("4")
    Thread.sleep(200)
    //等待协程作用域的结束，join方法
    //job.join()
    println("5")

    //输出为1,4,2,3,5
}

fun ScopeTest_Go(){
    //runBlockingTest()
    //globalScopeLaunchTest()
    CoroutineScopeLaunchTest()
}
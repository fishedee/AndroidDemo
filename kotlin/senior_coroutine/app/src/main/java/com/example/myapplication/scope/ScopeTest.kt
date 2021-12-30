package com.example.myapplication

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.math.log

fun ScopeContextTest(){
    runBlocking {
        //coroutineContext就是一个Key/value的容器而已
        val a = GlobalScope.launch(CoroutineName("k1")) {
            //CoroutineName是类，同时它有一个伴随对象，也是同名的CoroutineName
            GlobalScope.launch(CoroutineName("k2")) {
                //获取当前Scope的Job，Job是Job类的伴随对象
                println(coroutineContext[Job])
                //等价于这种写法
                println(coroutineContext[Job.Key])
                //获取当前Scope的Name
                println(coroutineContext[CoroutineName])
            }
            println(coroutineContext[Job])
            println(coroutineContext[CoroutineName])
        }
        a.join()
    }
}

fun ScopeDispacherWithContext(){
    runBlocking {
        val go1 = {
            println("current Id ${Thread.currentThread().id}")
        }
        for (i in (1..10)) {
            launch {
                withContext(Dispatchers.IO) {
                    go1()
                }
            }
        }
    }
    /*
    输出结构如下：
    current Id 12
    current Id 13
    current Id 16
    current Id 14
    current Id 16
    current Id 17
    current Id 15
    current Id 18
    current Id 14
    current Id 17
     */
}

fun ScopeDispacherTest(){
    //创建一个单线程的调度器
    val myDispatcher= Executors.newSingleThreadExecutor{ r -> Thread(r, "MyThread") }.asCoroutineDispatcher()
    runBlocking {
        //启动协程的时候指定调度器
        GlobalScope.launch(myDispatcher) {
            log(1)//MyThread线程
        }.join()
        log(2)//主线程
    }
    //用完要记得关闭调度器
    myDispatcher.close()
    /*
    输出结构如下：
    2021-12-16T08:42:19.643589Z [MyThread] 1
    2021-12-16T08:42:19.655755Z [main] 2
     */
}

fun ScopeDispatcherTest2(){
    runBlocking {
        Executors.newFixedThreadPool(10)
            .asCoroutineDispatcher().use { dispatcher ->
                GlobalScope.launch(dispatcher) {
                    //首次进入时调度1次
                    log(1)
                    //进入async调度1次
                    val job = async {
                        log(2)
                        delay(1000)
                        //delay返回来以后调度1次
                        log(3)
                        "Hello"
                    }
                    log(4)
                    val result = job.await()
                    //await 返回以后帝都1次
                    log("5. $result")
                }.join()
                log(6)
            }
    }
    /*
    输出结果如下：共产生4次调度，用了4个线程
    2021-12-16T08:40:47.317772Z [pool-1-thread-1] 1
    2021-12-16T08:40:47.333989Z [pool-1-thread-1] 4
    2021-12-16T08:40:47.334100Z [pool-1-thread-2] 2
    2021-12-16T08:40:48.340155Z [pool-1-thread-3] 3
    2021-12-16T08:40:48.341376Z [pool-1-thread-4] 5. Hello
     */
}

fun ScopeTest_Go(){
    //ScopeContextTest()
    ScopeDispacherWithContext()
    //ScopeDispacherTest()
    //ScopeDispatcherTest2()
}
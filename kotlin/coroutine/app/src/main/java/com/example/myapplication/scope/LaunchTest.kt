package com.example.myapplication.scope

import kotlinx.coroutines.*

fun MyLaunch_Go(){
    runBlocking {
        //launch其实是CoroutineScope的方法，外层必须有CoroutineScope才能调用
        println("1")
        //launch就是启动一个协程，不堵塞当前协程，无返回值
        // 但是launch的运行周期受子协程影响，结构性并发
        var job = launch{
            println("2")
            launch {
                println("3.1")
                delay(100)
                println("3.2")
            }
            launch {
                println("4.1")
                delay(200)
                println("4.2")
            }
            println("5")
        }
        println("6")
        job.join()
        println("7")
        //输出如下
        /*
        1
        6
        2
        5
        3.1
        4.1
        3.2
        4.2
        7
         */
    }
}

fun MyAsync_Go(){
    runBlocking {
        //async其实是CoroutineScope的方法，外层必须有CoroutineScope才能调用
        println("1")
        //async就是启动一个协程，不堵塞当前协程，有返回值
        // 但是async的运行周期受子协程影响，结构性并发
        var job = launch{
            println("2")
            var a1 = async {
                println("3.1")
                delay(100)
                println("3.2")
                "1"
            }
           var a2 = async {
                println("4.1")
                delay(200)
                println("4.2")
                "2"
            }
            println("5")
            //依然可以用join，但是没有返回值
            //a1.join()
            //对于async启动的块，我们可以用await获取返回值
            println("a1 is ${a1.await()}")
            println("a2 is ${a2.await()}")
        }
        println("6")
        job.join()
        println("7")
        //输出如下
        /*
        1
        6
        2
        5
        3.1
        4.1
        3.2
        a1 is 1
        4.2
        a2 is 2
        7
         */
    }
}

fun MyWithContext_Go(){
    runBlocking {
        //withContext其实是CoroutineScope的方法，外层必须有CoroutineScope才能调用
        println("1")
        //withContext就是启动一个协程，堵塞当前协程，有返回值
        //withContext与async很相似，不同之处在于，马上堵塞协程，且必须指定Dispatcher
        // 但是async的运行周期受子协程影响，结构性并发
        var job = launch{
            println("2")
            //参数指分派到IO线程池上执行
            var a1 = withContext(Dispatchers.IO) {
                println("3.1")
                delay(100)
                println("3.2")
                "1"
            }
            //和上面的写法是一致的
            var a2 = async(Dispatchers.IO)  {
                println("4.1")
                delay(200)
                println("4.2")
                "2"
            }.await()
            println("5")
            println("a1 is ${a1}")
            println("a2 is ${a2}")
        }
        println("6")
        job.join()
        println("7")
        //输出如下
        /*
        1
        6
        2
        3.1
        3.2
        4.1
        4.2
        5
        a1 is 1
        a2 is 2
        7
         */
    }
}

/*
// 这种是常见的写法错误，launch，async和withContext都是CoroutineScope的方法
// 在挂起函数中是没有CoroutineScope，所以这种方法是错误的
suspend fun mySuspend(){
    launch{
        println("cc")
    }
}
 */

//正确的方法是，先用coroutineScope获取当前的CoroutineScope
//然后再在CoroutineScope里面使用launch和async
suspend fun mySuspend2(){
    val job = coroutineScope {
        launch{
            println("3.1")
            delay(100)
            println("3.2")
        }
        async{
            println("4.1")
            delay(200)
            println("4.2")
            "HAHA"
        }
    }
    //coroutineScope的特点是隐式的join，无论你有没有调用，他都需要等待子协程结束才能返回
    //job.join()
}

fun MySuspendLanuch_Go(){
    runBlocking {
        println("1")
        //mySuspend()
        println("2")
        mySuspend2()
        println("5")
        /*
        输出结果：
        1
        2
        3.1
        4.1
        3.2
        4.2
        5
         */
    }
}

fun MySuspendLanuch2_Go(){
    runBlocking {
        println("1")
        //mySuspend()
        println("2")
        val job = coroutineScope {
            launch{
                println("3.1")
                delay(100)
                println("3.2")
            }
            async{
                println("4.1")
                delay(200)
                println("4.2")
                "HAHA"
            }
        }
        //coroutineScope的特点是隐式的join，无论你有没有调用，他都需要等待子协程结束才能返回
        //即使不在suspend方法中，它依然是这样
        //job.join()
        println("5")
        /*
        输出结果：
        1
        2
        3.1
        4.1
        3.2
        4.2
        3
         */
    }
}

suspend fun mySuspend3(){
    //supervisorScope与coroutineScope相似的工作方式，区别在于发生异常时的处理方式不同
    val job = supervisorScope {
        launch{
            println("3.1")
            delay(100)
            println("3.2")
        }
        async{
            println("4.1")
            delay(200)
            println("4.2")
            "HAHA"
        }
    }
    //supervisorScope的特点是隐式的join，无论你有没有调用，他都需要等待子协程结束才能返回
    //job.join()
}

fun MySuspendLanuch3_Go(){
    runBlocking {
        println("1")
        //mySuspend()
        println("2")
        mySuspend3()
        println("5")
        /*
        输出结果如下：
        1
        2
        3.1
        4.1
        3.2
        4.2
        5
         */
    }
}

fun LaunchTest_Go(){
    //MyLaunch_Go()
    //MyAsync_Go()
    //MyWithContext_Go()
    //MySuspendLanuch_Go()
    //MySuspendLanuch2_Go()
    MySuspendLanuch3_Go()
}
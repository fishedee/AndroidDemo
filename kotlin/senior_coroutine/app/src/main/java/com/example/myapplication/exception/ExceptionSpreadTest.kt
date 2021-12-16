package com.example.myapplication

import kotlinx.coroutines.*

fun ExceptionCatch(){
    //一般情况下，异常抛出以后，其他协程不受影响，仅仅会在全局打印异常错误
    runBlocking {
        var a = GlobalScope.launch {
            println("1")
            launch {
                println("3")
                launch {
                    println("4.1")
                    delay(30)
                    throw ArithmeticException("Hey!")
                    println("4.2")
                }
                launch {
                    //该协程受到影响，5.2无法输出
                    println("5.1")
                    delay(50)
                    println("5.2")
                }
                println("6")
                delay(100)
                //该协程受到影响，7无法输出
                println("7")
            }
            delay(100)
            //该协程受到影响，2无法输出
            println("2")
        }
        a.join()
        /*
        输出如下：
        1
        3
        4.1
        6
        5.1
         */
    }
}

fun ExceptionLaunchJoinCatch(){
    //作用域里面，使用async或者launch的join可以捕捉到异常，异常为
    runBlocking {
        var a = GlobalScope.launch {
            println("1")
            launch {
                println("3")
                var job = launch {
                    throw ArithmeticException("Hey!")
                }
                delay(100)
                //不执行
                println("4")
            }
            delay(100)
            //不执行
            println("2")
        }
        a.join()
        /*
        输出如下：
        1
        3
        */
    }
}

fun ExceptionAsyncJoinCatch(){
    //作用域里面，使用async或者launch的join可以捕捉到异常，异常为
    runBlocking {
        var a = GlobalScope.launch {
            println("1")
            launch {
                println("3")
                var job = async {
                    throw ArithmeticException("Hey!")
                }
                delay(100)
                //不执行
                println("4")
            }
            delay(100)
            //不执行
            println("2")
        }
        a.join()
        /*
        输出如下：
        1
        3
        */
    }
}

fun ExceptionAwaitCatch(){
    //作用域里面，使用async的await也可以捕捉到异常
    runBlocking {
        var a = GlobalScope.launch {
            println("1")
            launch {
                println("3")
                var job = async {
                    throw ArithmeticException("Hey!")
                    "cc"
                }
                delay(100)
                //不执行
                println("4")
            }
            delay(100)
            //不执行
            println("2")
        }
        a.join()
        /*
        1
        3
         */
    }
}

fun ExceptionWithContextCatch(){
    //作用域里面，使用withContext
    runBlocking {
        var a = GlobalScope.launch {
            println("1")
            launch {
                try{
                    println("3")
                    //withContext的异常能自动传递给上级，并且不会让其他协程自动退出
                    var job = withContext(Dispatchers.Default) {
                        throw ArithmeticException("Hey!")
                        "cc"
                    }
                    delay(100)
                    //这一句不执行
                    println("4")
                }catch(e: java.lang.Exception){
                    //注意，这一句能执行，捕获到原始异常
                    println("e ${e}")
                }
            }
            delay(100)
            //注意，这一句正常执行
            println("2")
        }
        a.join()
        /*
        1
        3
        e java.lang.ArithmeticException: Hey!
        2
         */
    }
}

fun ExceptionCoroutineScopeCatch(){
    //作用域里面，使用withContext
    runBlocking {
        var a = GlobalScope.launch {
            println("1")
            launch {
                println("3")
                coroutineScope {
                    println(5)
                    launch {
                        println(7)
                        throw ArithmeticException("Hey!")
                        //不执行
                        println(8)
                    }
                    launch {
                        println(9)
                        delay(100)
                        //不执行
                        println(10)
                    }
                    delay(100)
                    //不执行
                    println(6)
                }
                delay(100)
                //不执行
                println("4")
            }
            delay(100)
            //不执行
            println("2")
        }
        a.join()
        /*
        1
        3
        5
        7
        9
        Exception in thread "DefaultDispatcher-worker-2" java.lang.ArithmeticException: Hey!
         */
    }
}

fun ExceptionSupervisorScopeCatch(){
    //作用域里面，使用withContext
    runBlocking {
        var a = GlobalScope.launch {
            println("1")
            launch {
                println("3")
                supervisorScope {
                    println(5)
                    launch {
                        println(7)
                        throw ArithmeticException("Hey!")
                        //不执行
                        println(8)
                    }
                    launch {
                        println(9)
                        delay(100)
                        //执行
                        println(10)
                    }
                    delay(100)
                    //执行
                    println(6)
                }
                delay(100)
                //执行
                println("4")
            }
            delay(100)
            //执行
            println("2")
        }
        a.join()
        /*
        1
        3
        5
        7
        9
        Exception in thread "DefaultDispatcher-worker-2" java.lang.ArithmeticException: Hey!
         */
    }
}

fun ExceptionSpreadTest_Go(){
    //ExceptionCatch()
    //ExceptionLaunchJoinCatch()
    //ExceptionAsyncJoinCatch()
    //ExceptionAwaitCatch()
    ExceptionWithContextCatch()
    //ExceptionCoroutineScopeCatch()
    //ExceptionSupervisorScopeCatch()
}

/*
总结如下：
* 未捕获异常的传播：
* 默认情况下，对于同一个作用域下用launch或者async子协程的未捕获异常，会停止同一个作用域下面其他协程的正常运行
* 因为corountineScope沿用旧的作用域，所以corountineScope的子协程产生未捕获异常的时候，会连累原来的作用域一起崩掉
* 因为withContext会产生新的作用域，所以这个作用域下的异常不会影响上级作用域的协程。另外，withContext产生的异常会自动传递给他的上级协程
* 因为supervisorScope会产生新的作用域，所以这个作用域下的异常不会影响上级作用域的协程。另外，supervisorScope的各个子协程相互不受异常影响
 */
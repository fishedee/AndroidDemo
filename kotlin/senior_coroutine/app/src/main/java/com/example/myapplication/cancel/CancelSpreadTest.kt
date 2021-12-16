package com.example.myapplication.cancel

import com.example.myapplication.ExceptionWithContextCatch
import kotlinx.coroutines.*

fun CancelCatch(){
    runBlocking {
        var a = GlobalScope.launch {
            println("1")
            val job = launch {
                println("3")
                launch {
                    println("4.1")
                    delay(100)
                    //该协程受到影响，4.2无法输出
                    println("4.2")
                }
                launch {
                    //该协程受到影响，5.2无法输出
                    println("5.1")
                    delay(100)
                    println("5.2")
                }
                println("6")
                delay(100)
                //该协程受到影响，7无法输出
                println("7")
            }
            delay(100)
            job.cancel()
            //该协程受到影响，2无法输出
            println("2")
        }
        delay(30)
        a.cancel()
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

fun CancelWithContextCatch(){
    //作用域里面，使用withContext
    runBlocking {
        var a = GlobalScope.launch {
            println("1")
            launch {
                println("3")
                //withContext的异常能自动传递给上级，并且不会让其他协程自动退出
                var job = withContext(Dispatchers.Default) {
                    println(5)
                    delay(100)
                    //这一句不执行
                    println(6)
                }
                delay(100)
                //这一句不执行
                println("4")
            }
            delay(100)
            //这一句不执行
            println("2")
        }
        delay(30)
        a.cancel()
        /*
        1
        3
         */
    }
}

fun CancelCoroutineScopeCatch(){
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
                        delay(100)
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
        delay(30)
        a.cancel()
        /*
        1
        3
        5
        7
        9
         */
    }
}

fun CancelSupervisorScopeCatch(){
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
                        delay(100)
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
        delay(30)
        a.cancel()
        /*
        1
        3
        5
        7
        9
        */
    }
}

fun CancelSpreadTest_Go(){
    //CancelCatch()
    //CancelWithContextCatch()
    //CancelCoroutineScopeCatch()
    CancelSupervisorScopeCatch()
}

/*
总结如下：
* 协程取消的逻辑：
* 按照协程结构块的规则，顶级协程cancel的时候，将下面所有的协程都cancel掉，无论是不是同一个作用域
*/
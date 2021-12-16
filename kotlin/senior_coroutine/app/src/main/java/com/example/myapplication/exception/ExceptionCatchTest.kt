package com.example.myapplication.exception

import com.example.myapplication.log
import kotlinx.coroutines.*
import java.lang.Exception

fun ExceptionDefaultCatch(){
    runBlocking {
        //设置异常处理
        val exceptionHandler = CoroutineExceptionHandler() { coroutineContext, throwable ->
            log("Throws an exception with message: ${throwable.message}")
        }

        log(1)

        //启动协程的时候，可以指定默认的异常处理方式
        GlobalScope.launch(exceptionHandler) {
            throw ArithmeticException("Hey!")
        }.join()
        log(2)
    }
    /*
    1
    2
    3
    e java.lang.ArithmeticException: Hey!
    Exception in thread "DefaultDispatcher-worker-2" java.lang.ArithmeticException: Hey!
     */
}

fun ExceptionDefaultCatch_supervisorScope(){
    runBlocking {
        //设置异常处理
        val exceptionHandler = CoroutineExceptionHandler() { coroutineContext, throwable ->
            log("Throws an exception with message: ${throwable.message} ${coroutineContext[CoroutineName]}")
        }

        log(1)

        //一般情况下，由顶级的Scope来捕捉未捕获异常
        //当有supervisorScope的时候，由supervisorScope下面的首个顶级launch来捕获一会吃那个
        GlobalScope.launch(exceptionHandler+CoroutineName("1")) {
            log(3)
            launch {
                supervisorScope {
                    launch(exceptionHandler+CoroutineName("2")) {
                        launch( exceptionHandler+CoroutineName("3")) {
                            throw ArithmeticException("Hey!")
                        }
                    }
                }
            }

            log(4)
        }.join()
        log(2)
        /*
        输出结果如下：
        2021-12-16T11:46:56.713518Z [main] 1
        2021-12-16T11:46:56.737191Z [DefaultDispatcher-worker-1] 3
        2021-12-16T11:46:56.738443Z [DefaultDispatcher-worker-1] 4
        2021-12-16T11:46:56.744317Z [DefaultDispatcher-worker-2] Throws an exception with message: Hey! CoroutineName(2)
        2021-12-16T11:46:56.745125Z [main] 2
         */
    }
}

fun ExceptionDefaultCatch_coroutineScope(){
    runBlocking {
        //设置异常处理
        val exceptionHandler = CoroutineExceptionHandler() { coroutineContext, throwable ->
            log("Throws an exception with message: ${throwable.message} ${coroutineContext[CoroutineName]}")
        }

        log(1)

        //一般情况下，由顶级的Scope来捕捉未捕获异常
        //当有supervisorScope的时候，由supervisorScope下面的首个顶级launch来捕获一会吃那个
        GlobalScope.launch(exceptionHandler+CoroutineName("1")) {
            log(3)
            launch {
                coroutineScope {
                    launch(exceptionHandler+CoroutineName("2")) {
                        launch( exceptionHandler+CoroutineName("3")) {
                            throw ArithmeticException("Hey!")
                        }
                    }
                }
            }

            log(4)
        }.join()
        log(2)
        /*
        输出结果如下：
        2021-12-16T11:46:56.713518Z [main] 1
        2021-12-16T11:46:56.737191Z [DefaultDispatcher-worker-1] 3
        2021-12-16T11:46:56.738443Z [DefaultDispatcher-worker-1] 4
        2021-12-16T11:46:56.744317Z [DefaultDispatcher-worker-2] Throws an exception with message: Hey! CoroutineName(1)
        2021-12-16T11:46:56.745125Z [main] 2
         */
    }
}

fun ExceptionDefaultCatch_withContext(){
    runBlocking {
        //设置异常处理
        val exceptionHandler = CoroutineExceptionHandler() { coroutineContext, throwable ->
            log("Throws an exception with message: ${throwable.message} ${coroutineContext[CoroutineName]}")
        }

        log(1)

        //一般情况下，由顶级的Scope来捕捉未捕获异常
        //当有supervisorScope的时候，由supervisorScope下面的首个顶级launch来捕获一会吃那个
        GlobalScope.launch(exceptionHandler+CoroutineName("0")) {
            log(3)
            launch {
                withContext(Dispatchers.IO + exceptionHandler + CoroutineName("1")) {
                    launch(exceptionHandler + CoroutineName("2")) {
                        launch(exceptionHandler + CoroutineName("3")) {
                            throw ArithmeticException("Hey!")
                        }
                    }
                }
            }
            delay(100)
            log(4)
        }.join()
        delay(100)
        log(2)
        /*
        输出结果如下：
        2021-12-16T11:46:56.713518Z [main] 1
        2021-12-16T11:46:56.737191Z [DefaultDispatcher-worker-1] 3
        2021-12-16T11:46:56.738443Z [DefaultDispatcher-worker-1] 4
        2021-12-16T11:46:56.744317Z [DefaultDispatcher-worker-2] Throws an exception with message: Hey! CoroutineName(1)
        2021-12-16T11:46:56.745125Z [main] 2
         */
    }
}


fun ExceptionCatchTest_Go(){
    //ExceptionDefaultCatch()
    ExceptionDefaultCatch_supervisorScope()
    //ExceptionDefaultCatch_coroutineScope()
    //ExceptionDefaultCatch_withContext()
}

/*
总结如下：
* 未捕获异常的捕捉：
* 默认情况下，异常总是冒泡到当前作用域顶部被捕捉到
* 使用supervisorScope的时候，异常最多只能被supervisorScope的最近launch或者async捕捉到
* 使用withContext但没有被捕捉的时候，依然被顶部作用域捕捉
*/
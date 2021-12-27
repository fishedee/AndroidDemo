package com.example.myapplication.scope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.RuntimeException

fun testFlowCatchDefault(){
    runBlocking {
        flow{
            //看起来代码只是catch了发射方的异常
            //但是，实际运行，会连发射方的异常都收集了
            //根本原因是，flow是闭环输入，被动输出，emit里面会执行collect的代码
            try{
                for( i in (1..3)){
                    emit(i)
                }
            }catch(e:Exception){
                println("all catch")
                e.printStackTrace()
            }
        }.collect {
            println(it)
            if( it == 2 ){
                throw RuntimeException("ee")
            }
        }
    }
    /*
    输出如下：
    1
    2
    all catch
    java.lang.RuntimeException: ee
        at com.example.myapplication.scope.FlowCatchKt$testFlowCatchDefault$1$invokeSuspend$$inlined$collect$1.emit(Collect.kt:137)
        at kotlinx.coroutines.flow.internal.SafeCollectorKt$emitFun$1.invoke(SafeCollector.kt:15)
        at kotlinx.coroutines.flow.internal.SafeCollectorKt$emitFun$1.invoke(SafeCollector.kt:15)
     */
}

fun testFlowCatchOperation(){
    runBlocking {
        try{
            flow{
                for( i in (1..3)){
                    emit(i)
                }
                //catch操作符只会收集在操作符之前的异常的操作
                //因此不会收集collect里面的异常
            }.catch {e->
                println("emit catch")
                e.printStackTrace()
            }.collect {
                println(it)
                if( it == 2 ){
                    throw RuntimeException("ee")
                }
            }
            //collect的异常需要用外部的try-catch来捕捉
        }catch( e:Exception){
            println("collect catch")
            e.printStackTrace()
        }
    }
    /*
    输出如下：
    1
    2
    collect catch
    java.lang.RuntimeException: ee
        at com.example.myapplication.scope.FlowCatchKt$testFlowCatchOperation$1$invokeSuspend$$inlined$collect$1.emit(Collect.kt:137)
        at kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$$inlined$collect$1.emit(Collect.kt:136)
        at kotlinx.coroutines.flow.internal.SafeCollectorKt$emitFun$1.invoke(SafeCollector.kt:15)
        at kotlinx.coroutines.flow.internal.SafeCollectorKt$emitFun$1.invoke(SafeCollector.kt:15)
        at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:77)
        at kotlinx.coroutines.flow.internal.SafeCollector.emit(SafeCollector.kt:59)
     */
}

fun FlowCatchTest_Go() {
    testFlowCatchDefault()
    //testFlowCatchOperation()
}
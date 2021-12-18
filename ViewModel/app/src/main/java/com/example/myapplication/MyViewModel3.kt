package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MyTimeout{
    fun wait(timeout:Long,callback:(target:Int)->Unit):Thread{
        val thread = Thread{
            try{
                Thread.sleep(timeout)
                callback(100)
            }catch(e:InterruptedException){
                Log.d("ViewModel","catch thread interrupt")
            }
        }
        thread.start()
        return thread
    }
}

suspend fun suspendCoroutine_1():Int{
    //注意改为了suspendCancellableCoroutine，而不是suspendCoroutine
    return suspendCancellableCoroutine{ continuation->
        val thread = MyTimeout().wait(5000) { result ->
            continuation.resume(result){
                //resume的时候发现协程取消了
                //do nothing
            }
        }
        //关键是这一句，invokeOnCancellation的意义是，当收到协程cancel的时候，得到额外的通知
        //这样可以快速停止普通作用域的操作，例如及时停止线程池中对网络请求的发送（OkHttp），释放回调避免对View的内存占用（AutoDispose），
        continuation.invokeOnCancellation {
            //通知线程interrupt
            Log.d("ViewModel","cancel!!!")
            thread.interrupt()
        }

    }
}


class MyViewModel3 :ViewModel(){
    var counter = 0
    var job:Job? = null

    suspend fun inc(){
        this.job?.cancel()
        this.job = viewModelScope.launch {
            suspendCoroutine_1()
            counter++
        }
        this.job?.join()
    }

    fun cancel(){
        //一旦取消，ViewModelScope不再响应任何launch
        //viewModelScope.cancel()

        this.job?.cancel()
    }

    fun get():Int{
        return this.counter
    }
}
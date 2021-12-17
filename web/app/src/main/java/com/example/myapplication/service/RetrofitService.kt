package com.example.myapplication.service;

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object RetrofitService {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.68:8585/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val appService = retrofit.create(AppService::class.java)

    suspend fun <T> Call<T>.await():T{
        return suspendCoroutine { continuation ->
            enqueue(object:Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if( body != null ){
                        continuation.resume(body)
                    }else{
                        continuation.resumeWithException(RuntimeException("response body is null"))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}

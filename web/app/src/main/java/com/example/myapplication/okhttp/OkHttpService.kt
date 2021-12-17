package com.example.myapplication.okhttp

import android.util.Log
import com.example.myapplication.Person
import com.example.myapplication.service.Country
import com.example.myapplication.service.Result
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Query
import java.io.IOException
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object OkHttpService {
    val client = OkHttpClient()

    val gson: Gson

    init{
        val gsonBuilder = GsonBuilder()
        gsonBuilder.serializeNulls()
        gsonBuilder.disableHtmlEscaping()
        gson = gsonBuilder.create()
    }

    //注意，这里需要用inline+reified
    inline suspend fun <reified T> Call.await():T{
        val typeOf = object: TypeToken<T>(){}.type
        return suspendCoroutine { continuation ->
            enqueue(object: Callback {
                override fun onResponse(call: Call, response: okhttp3.Response) {
                    val bodyStr = response.body?.string()
                    if( bodyStr != null ){
                        Log.d("OkHttp",bodyStr)
                        val body = gson.fromJson<T>(bodyStr,typeOf)
                        continuation.resume(body)
                    }else{
                        continuation.resumeWithException(RuntimeException("response body is null"))
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    continuation.resumeWithException(e)
                }
            })
        }
    }

    fun get1():Call{
        val request = Request.Builder()
            .url("http://192.168.1.68:8585/hello/get1")
            .get()
            .build()
        return client.newCall(request)
    }

    suspend  fun get2(user:String, myHeader: String):Result<String?>{
        var url = HttpUrl.Builder()
            .scheme("http")
            .host("192.168.1.68")
            .port(8585)
                //不能合并多个segments，不能这样写/hello/get2
            .addPathSegment("hello")
            .addPathSegment("get2")
            .addQueryParameter("user", user)
            .build();

        Log.d("OkHttp","get2 url ${url}")


        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("my",myHeader)
            .build()

        return client.newCall(request).await()
    }

    suspend  fun post1(country: Country, myHeader: String):Result<String?>{
        var url = HttpUrl.Builder()
            .scheme("http")
            .host("192.168.1.68")
            .port(8585)
            .addPathSegment("hello")
            .addPathSegment("post1")
            .build();

        val body = gson.toJson(country)

        val requestBody = body.toRequestBody("application/json; charset=utf-8".toMediaType())

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .addHeader("my",myHeader)
            .build()

        return client.newCall(request).await()
    }
}
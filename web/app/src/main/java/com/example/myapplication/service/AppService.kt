package com.example.myapplication.service

import retrofit2.Call
import retrofit2.http.*

data class Country(val name:String,val persons:List<Person>){
    data class Person(val name:String,val age:Int)
}
interface AppService {
    @GET("/hello/get1")
    fun get1():Call<Result<String?>>

    @GET("/hello/get2")
    fun get2(@Query("user")user:String,@Header("my") myHeader: String):Call<Result<String?>>

    @POST("/hello/post1")
    fun post1(@Body data:Country, @Header("my") myHeader: String):Call<Result<String?>>
}
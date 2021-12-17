package com.example.myapplication.service

data class Result<T>(var code:Int,val message:String,val data:T)
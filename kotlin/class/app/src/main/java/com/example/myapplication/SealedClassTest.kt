package com.example.myapplication

import java.lang.Exception

sealed class Result{
    private val age:Int = 12
}

//有括号，是一个类
class Success:Result(){}

class Fail:Result(){}

interface Result2

//没有括号，是一个接口
class Success2:Result2{}

class Fail2:Result2{}

fun showResult2(input:Result2) = when(input){
    is Success2->println("success")
    is Fail2->println("fail")
    //普通interface类型的时候，缺少了else操作会报错，因为存在非Success2或者非Fail2的情况。
    // 因为，其他代码文件中可能存在实现Result2接口的类
    else ->throw Exception("123")
}

fun showResult(input:Result) = when(input){
    is Success->println("success")
    is Fail->println("fail")
    //sealed类型，因为sealed class规定所有继承sealed class的其他类必须在同一个代码文件中，因此不需要else操作
}

fun SealedClassTest_Go(){
    var result2:Result2 = Success2()

    showResult2(result2)

    var result:Result = Success()

    showResult(result)
}
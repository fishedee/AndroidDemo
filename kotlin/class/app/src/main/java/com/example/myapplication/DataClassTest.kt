package com.example.myapplication

//默认的data class没有空构造函数，通过默认参数的方式，给与空构造函数
data class Phone(val brand:String = "",val price:Double = 0.0) {

}

fun DataClassTest_Go(){
    var a = Phone("MOTO",1.1)
    var b = Phone()
    var c = Phone("MOTO",1.1)

    //data class默认有equals方法
    println("a == c 为 "+(a==c))
    println("a == b 为 "+(a==b))

    //data class默认有toString方法
    println("a.toString "+ a.toString())

    //data class默认有hashCode方法
    println("a.hasCode "+ a.hashCode())

}
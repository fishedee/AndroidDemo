package com.example.myapplication

class MyClass{
    //私有权限，仅当前类可以看到
    private var a = 1

    //保护权限，当前类，子类可以看到
    protected var b = 3

    //internal权限，仅同一个模块中类可以看到，（与Java的不同）
    internal var d:Boolean = true

    //公开权限，所有类可以看到，默认方式
    public val c:String = "123"
}

fun AccessTest_Go(){
    var myClass = MyClass()
    println("c = ${myClass.c} d= ${myClass.d}")
}
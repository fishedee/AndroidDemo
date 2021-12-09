package com.example.myapplication

class MyPerson{
    //由开发者来确定它是否已经初始化了
    //编译时，编译器都认为它是非空的
    private lateinit var target:String;

    fun isInit():Boolean{
        return ::target.isInitialized
    }
    fun get():String{
        return target;
    }

    fun set(a:String){
        this.target = a;
    }
}

fun LateInitTest_Go(){
    var person = MyPerson()
    println("hasInit ${person.isInit()}")
    person.set("fish")
    println(person.get())
    println("hasInit ${person.isInit()}")

    var person2 = MyPerson()
    println("hasInit ${person2.isInit()}")
    //这里运行时会报错，因为读取对象的时候，发现对象还没有被初始化
    //lateinit property target has not been initialized
    println(person2.get())
}
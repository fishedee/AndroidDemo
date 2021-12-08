package com.example.myapplication

//注意没有class关键字，object表示是单例方式的类
//Kotlin会为这个类型自动生成一个唯一的伴生对象，该对象就是单例
object Single {
    private var count = 1;
    fun inc(){
        count ++;
    }
    fun get():Int{
        return count;
    }
}

fun SingletonClassTest_Go(){
    println("count ${Single.get()}")
    Single.inc()
    println("count ${Single.get()}")
}
package com.example.myapplication

import kotlin.properties.Delegates

class example2 {
    //lazy，惰性获取变量，变量只能是只读的
    //该方法是线程安全的
    val no:Int by lazy {
        println("begin get no")
        200
    }

    //observable，可观察数据，首个参数的默认值，闭包是修改时候触发的
    var name: String by Delegates.observable("wang", {
        kProperty, oldName, newName ->
        println("kProperty：${kProperty.name} | oldName:$oldName | newName:$newName")
    })

    //notNull，与lateinit的效果一样，只是支持基元类型
    var instance:Int by Delegates.notNull()
}
fun LazyByTest(){
    val a = example2()
    println("after init a")
    println(a)
    println("get a")
    println(a.no)
    println(a.no)
}

fun ObservableByTest(){
    val a = example2()
    println("after init a")
    println(a)
    println("get a")
    println(a.name)
    a.name = "bb"
    a.name = "cc"
}

fun NotNullByTest(){
    val a = example2()
    //这里会产生运行是报错
    //println(a.instance)

    a.instance = 123
    println(a.instance)
}

fun BuiltInByPropertyTest_Go(){
    LazyByTest()
    ObservableByTest()
    NotNullByTest()
}
package com.example.myapplication

fun ListTest(){
    var a = listOf("a","b")

    //listOf 创建的是不可变集合，所以不能被Add
    //a.add("c")

    //读取下标
    println(a[0])

    //遍历list，不需要声明single
    for( single in a ){
        println(single)
    }
}

fun ListTest2(){
    var b = mutableListOf("a","b")

    //可修改集合
    b.add("c")

    //读取下标
    println(b[0])

    //遍历list
    for( single in b ){
        println(single)
    }
}

fun MapTest(){
    var a = mapOf("A" to 1,"B" to 2)

    //不可变的Map，不能被修改
    //a["B"] = 3;

    //读取下标
    println(a["A"])
    println(a["C"])//不存在的时候返回null

    //遍历map
    for( (key,value) in a ){
        println("key is ${key} value is ${value}")
    }
}

fun MapTest2(){
    var a = mutableMapOf("A" to 1,"B" to 2)

    //不变的Map，能被修改
    a["B"] = 3;

    //读取下标
    println(a["A"])
    println(a["C"])

    //遍历map
    for( (key,value) in a ){
        println("key is ${key} value is ${value}")
    }
}

fun CollectionTest_Go(){
    ListTest()
    ListTest2()
    MapTest()
    MapTest2()
}
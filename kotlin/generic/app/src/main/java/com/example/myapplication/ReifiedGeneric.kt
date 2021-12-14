package com.example.myapplication

//以下的写法会报错，因为Java的泛型是编译时的，运行时会擦除类型
/*
fun <T> getClass(input:T):Class<T> = {
    return T::class.java
}
 */

//使用inline和reified关键字，能实现编译时泛型展开（实化），所以就能实现这样的代码
inline fun <reified T> getClass(input:T):Class<out T> {
    return input!!::class.java
}

//甚至不需要传入参数
inline fun <reified T> getClass2():Class<T> {
    return T::class.java
}

fun ReifiedGenericTest_Go(){
    println("class1 ${getClass(1)}")
    println("class1 ${getClass("fish")}")

    println("class2 ${getClass2<Int>()}")
    println("class2 ${getClass2<String>()}")
}


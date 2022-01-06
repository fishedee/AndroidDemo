package com.example.myapplication

fun <T> printTemp(a:T){
    println(a.toString()+"_temp")
}

//带泛型上界的声明
fun <T :Person > printPerson(a:T){
    println(a.name+","+a.age);
}

open class Person(val name:String,val age:Int)

class Student:Person("student",123)

class SimpleData<T>{
    private var data:T? = null

    fun set(t:T?){
        this.data = t
    }

    fun get():T?{
        return this.data
    }
}

//函数参数接受任意的泛型类型
fun printAllSimpleData(data:SimpleData<*>){
    val re = data.get()
    println("re ${re}")
}

fun BasicGenericTest_Go(){
    printTemp("123Hello")
    printTemp(78)
    printTemp(Person("fish",123))

    printPerson(Person("cat",780))
    printPerson(Student())

    val data1 = SimpleData<Int>()
    data1.set(123)
    println("data1 ${data1.get()}")

    var data2 = SimpleData<Person>()
    data2.set(Person("cat",879))
    println("data2 ${data2.get()}")
    //这里会报错，因为类型不匹配
    //data2.set(123)

    //可以传入不同的泛型参数
    printAllSimpleData(data1)
    printAllSimpleData(data2)
}
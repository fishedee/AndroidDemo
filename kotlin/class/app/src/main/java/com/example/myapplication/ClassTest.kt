package com.example.myapplication

//默认是不可以被继承的
class Person {

    //默认成员为public
    var name = ""
    var age = 0

    fun eat(){
        println(name +" is eating. He is "+ age +" years old")
    }
}

fun ClassTest_Go(){
    val p = Person()
    p.age = 121
    p.name = "Fish"
    p.eat()
}
package com.example.myapplication

//注意，这里的接口有个fun关键字，相当于Java里面的@FunctionalInterface注解
fun interface MyAnimal{
    fun call():String
}

fun goAnimal(animal:MyAnimal){
    println("animal call [${animal.call()}]")
}

class MyDog:MyAnimal{
    override fun call(): String {
        return "dog call"
    }
}

fun AnonymousClassTest_Go(){
    //明确地创建一个类
    goAnimal(MyDog())

    //匿名类
    goAnimal(object :MyAnimal{
        override fun call():String{
            return "Cat call"
        }
    })

    //可以用闭包传入参数，但是需要interface声明时有fun关键字才行
    goAnimal{
        "Closure Call"
    }
}
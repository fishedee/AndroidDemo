package com.example.myapplication

data class Person(val name:String,val age:Int){

}

fun BuiltInLambdaTest_Go(){
    val a = Person("fish",123)
    //let 关键字，相当于js的with，只是需要明确指定一个参数可以
    a.let{
        person->
            println("age is ${person.age}")
            println("age is ${person.age}")
    }

    //等效于以上的效果，用it关键字能更省事一点
    a.let{
        println("age is ${it.age}")
        println("age is ${it.age}")
    }
}
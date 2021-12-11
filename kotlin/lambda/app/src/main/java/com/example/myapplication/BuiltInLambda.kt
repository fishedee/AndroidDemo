package com.example.myapplication

import java.io.BufferedOutputStream
import java.io.FileOutputStream

data class Person(private var name:String,private var age:Int){
    fun incAge(){
        this.age = this.age+1
    }

    fun getAge():Int{
        return this.age
    }
}

fun let_Go(){

    val a = Person("fish",123)
    //let 关键字，相当于js的with，只是需要明确指定一个参数可以
    a.let{
            person->
        println("age is ${person.getAge()}")
        println("age is ${person.getAge()}")
    }

    //等效于以上的效果，用it关键字能更省事一点
    a.let{
        println("age is ${it.getAge()}")
        println("age is ${it.getAge()}")
    }
}

fun with_Go(){
    var a = Person("fish",123)
    //let 关键字，与js的with，完全等价，可以直接调用对象的方法和变量。
    // 另外，最后一个语句的值，作为整个with语句的结果。
    var newAge = with(a){
        incAge()
        incAge()
        getAge()
    }
    println("new Age ${newAge}")
}

fun run_Go(){
    var a = Person("fish",123)
    //与let关键字等价，语法不同而已
    var newAge = a.run{
        incAge()
        incAge()
        getAge()
    }
    println("new Age ${newAge}")
}

fun apply_Go(){
    var a = Person("fish",123)
    //与let关键字相似，返回值是对象自身，而不是最后一个语句的值
    var newA = a.apply{
        incAge()
        incAge()
        getAge()
    }
    println("new Age ${newA}")
}

fun use_Go(){
    val fo = FileOutputStream("data")
    val bfo = BufferedOutputStream(fo)
    //外层流关闭的时候，内层流就会关闭
    //use会在闭包结束的时候自动调用close
    bfo.use {
        bfo.write("Hello".toByteArray())
    }
    bfo.close()
}


fun BuiltInLambdaTest_Go(){
    let_Go()
    with_Go()
    run_Go()
    apply_Go()
    use_Go()
}
package com.example.myapplication

fun String.lettersCount():Int{
    var count = 0
    for( char in this ){
        if( char.isLetter() ){
            count++
        }
    }
    return count
}

//使用泛型，可以对任何类型进行方法扩展
fun <A> A.with(b:String):String{
    return this.toString()+"#"+b;
}

data class testClass(val name:String,val age:Int)

fun ExtendFunctionTest_Go(){
    var a = "21 Hello22"
    println("lettersCount ${a.lettersCount()}")

    var b = "b".with("c")
    println("b ${b}")

    var c = testClass("fish",123).with("kk")
    println("c ${c}")
}
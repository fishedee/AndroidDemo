package com.example.myapplication

//infix是一种语法糖，可以简化函数的调用语法，不需要点号，和括号来引用参数
//限制在于，必须是某个类的成员方法或者扩展方法，方法参数有且仅有一个参数
infix fun String.beginsWithFish(prefix:String):Boolean{
    return this.startsWith(prefix+"#")
}

fun InfixFunctionTest_Go(){
    var a = "ccasd"
    var b = "cc#adsf"
    println("a beginsWithFish ${a beginsWithFish "cc"}")
    println("b beginsWithFish ${b beginsWithFish "cc"}")
}
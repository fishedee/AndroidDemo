package com.example.myapplication

enum class Color {
    RED,
    GREEN,
    BLUE,
}

fun enumTest1(){
    val output = {input:Color->
        println("name ${input.name} ordinal ${input.ordinal}")
    }
    output(Color.RED)
    output(Color.GREEN)
    output(Color.BLUE)
}


enum class Color2(val index:String,val aliasName:String) {
    RED("#FF0000","红色"),
    GREEN("#00FF00","绿色"),
    BLUE("#0000FF","蓝色"),
}

fun enumTest2(){
    val output = {input:Color2->
        println("name ${input.name} ordinal ${input.ordinal} index ${input.index} alias ${input.aliasName}")
    }
    output(Color2.RED)
    output(Color2.GREEN)
    output(Color2.BLUE)
}

fun EnumTest_Go(){
    enumTest1()
    enumTest2()
}
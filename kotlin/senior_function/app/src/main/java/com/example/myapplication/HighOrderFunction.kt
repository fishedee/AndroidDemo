package com.example.myapplication

//Unit相当于Void类型
fun num1AndNum2(num1:Int,num2:Int,operatorion:(Int,Int)->Unit):Unit{
    operatorion(num1,num2)
}

fun print(num1:Int,num2:Int){
    println("num1 ${num1} and num2 ${num2}")
}

//一个特殊的闭包，类似apply方式的闭包，stringBuilder是该闭包的this参数
fun build(myBuilder:StringBuilder,block:StringBuilder.()->Unit):StringBuilder{
    block(myBuilder)
    return myBuilder
}

fun HighOrderFunctionText_Go(){
    val a = 10
    var b = 20
    //传入一个已存在的全局函数，用:;
    num1AndNum2(a,b,::print)
    //传入一个闭包
    num1AndNum2(a,b){c,d->
        println("c = ${c} and d = ${d}")
    }

    val c = StringBuilder()
    val d = build(c){
        //调用的都是StringBuilder里面的方法
        append(10)
        append("cc")
    }
    println("d is ${d}")
}
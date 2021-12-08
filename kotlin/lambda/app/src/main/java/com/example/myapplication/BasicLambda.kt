package com.example.myapplication

fun BasicLambdaTest_Go(){
    val list = listOf("Apple","Banana","Orange","Pear","Grape")

    //lambda的基础写法
    val r1 = list.map({
        fruit:String->fruit.length
    })
    println("r1 is ${r1}")


    //lambda作为函数的最后一个参数的时候，可以移出到括号外面
    val r2 = list.map(){
            fruit:String->fruit.length
    }
    println("r1 is ${r2}")

    //map函数的参数为空的时候，可以省略括号
    val r3 = list.map{
            fruit:String->fruit.length
    }
    println("r1 is ${r3}")

    //可以省略lambda的参数类型，由kotlin来自己推导
    val r4 = list.map{
            fruit->fruit.length
    }
    println("r1 is ${r4}")

    //使用it关键字，来代表lambda的唯一参数的情况，这样能避免尖括号
    val r5 = list.map{
            it.length
    }
    println("r1 is ${r5}")
}
package com.example.myapplication

fun whileLoop():String{
    var out = ""
    var a = 0
    while( a <= 10 ){
        out = out+a+" "
        a++
    }
    return out
}

fun forLoop():String{
    var out = ""
    //左闭右闭区间，只能升序
    for( i in 0..10){
        out = out + i +" "
    }
    return out
}

fun forLoop2():String{
    var out = ""
    //左闭右开区间，只能升序
    for( i in 0 until 10 ){
        out = out + i +" "
    }
    return out
}


fun forLoop3():String{
    var out = ""
    //左闭右开区间，升序，且设置步长
    for( i in 0 until 10 step 2 ){
        out = out + i +" "
    }
    return out
}

fun forLoop4():String{
    var out = ""
    //左闭右闭区间，只能降序
    for( i in 10 downTo 1 ){
        out = out + i +" "
    }
    return out
}



fun LoopTest_Go(){
    println(whileLoop())
    println(forLoop())
    println(forLoop2())
    println(forLoop3())
    println(forLoop4())
}
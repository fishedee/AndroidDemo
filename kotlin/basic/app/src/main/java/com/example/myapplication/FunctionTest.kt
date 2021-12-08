package com.example.myapplication

fun maxNumber(a:Int,b:Int):Int{
    if( a > b){
        return a;
    }else{
        return b
    }
}

fun maxNumber2(a:Int,b:Int):Int = Math.max(a, b);

fun FunctionTest_Go(){
    println(maxNumber(1,2));
    println(maxNumber2(2,3));
}

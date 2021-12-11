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
fun ExtendFunctionTest_Go(){
    var a = "21 Hello22"
    println("lettersCount ${a.lettersCount()}")
}
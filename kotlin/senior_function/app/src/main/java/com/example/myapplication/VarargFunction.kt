package com.example.myapplication

//Any 类型是非空的
//vararg 是不定参数的关键字
fun cvOf(vararg pairs:Pair<String,Any?>):Map<String,Any>{
    val result = HashMap<String,Any>()
    for( pair in pairs ){
        var key = pair.first
        var value = pair.second
        when(value){
            is Int->result.put(key,value)
            is String->result.put(key,value)
            null->{
                println("key ${key} is null")
            }
        }
    }
    return result
}

fun VarargFunctionTest_Go(){
    val result = cvOf("a" to 1,"b" to "fish","c" to 12,"d" to null)
    println(result)
}
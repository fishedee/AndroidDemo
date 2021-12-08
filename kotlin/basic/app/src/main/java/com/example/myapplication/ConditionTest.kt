package com.example.myapplication

fun IfConfidition(a:Int,b:Int):Int{
    if( a > b ){
        return a;
    }else{
        return b;
    }
}

fun IfConfidition2(a:Int,b:Int):Int{
    //if语句也可以有返回值
    var result = if( a > b ){
        a
    }else{
        b
    }
    return result;
}

fun IfConfidition3(a:Int,b:Int) = if( a> b) {
    a
}else{
    b
}

fun whenCondition(a:Int):String{
    return when(a){
        1->"One"
        2->"Two"
        else->"Other"
    }
}

fun whenConditionForType(a:Number):String{
    return when(a){
        is Int->"Int"
        is Double->"Double"
        else->"Other"
    }
}

fun whenConditionForExpression(name:String):Int{
    //when的参数为空
    return when{
        name.startsWith("Fish_")->100
        //不需要用equals来比较
        name == "Jim"->200
        else->-1
    }
}

fun ConditionTest_Go(){
    println(IfConfidition(1,2))
    println(IfConfidition2(2,3))
    println(IfConfidition3(3,4))

    println(whenCondition(2))
    println(whenConditionForType(1.1))
    println(whenConditionForExpression("Fish_gg"))
}
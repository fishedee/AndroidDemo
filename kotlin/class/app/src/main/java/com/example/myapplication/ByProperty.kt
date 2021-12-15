package com.example.myapplication

import kotlin.reflect.KProperty

class ReadOnlyPropertyDelegate{
    operator fun getValue(thisRef:Any?,property:KProperty<*>):String{
        return "${thisRef?.javaClass}, thank you for delegating '${property.name}' to me!"
    }
}

class WritePropertyDelegate{
    operator fun getValue(thisRef:Any?,property:KProperty<*>):String{
        return "${thisRef?.javaClass}, thank you for delegating '${property.name}' to me!"
    }
    operator fun setValue(thisRef:Any?,property:KProperty<*>,value:String){
        println("${value} has been assigned to '${property.name}' in ${thisRef?.javaClass}")
    }
}

//属性的by委托，就是将属性的读写操作委托到另外一个类去
class ByPropertyExample{
    //val声明，只读
    val str:String by ReadOnlyPropertyDelegate()

    //var声明，可读写
    var str2:String by WritePropertyDelegate()
}

fun ByPropertyTest_Go(){
    val example = ByPropertyExample()

    println(example.str)
    println(example.str2)
    example.str2 = "789"
}
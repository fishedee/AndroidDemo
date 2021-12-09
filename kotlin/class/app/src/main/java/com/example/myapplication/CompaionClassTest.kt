package com.example.myapplication

class Util {
    fun doAction1(){
        println("do action1")
    }
    companion object{
        //伴生对象，一个具体的实例，不是Java的static方法
        fun doAction2(){
            println("do Action2")
        }

        //与Java一样的static方法，以方便与Java对象进行交互
        @JvmStatic
        fun doAction3(){
            println("do Action3")
        }
    }
}

fun CompaionClassTest_Go(){
    var util = Util()
    util.doAction1()

    Util.doAction2()
    Util.doAction3()
}
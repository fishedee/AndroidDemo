package com.example.myapplication

/*
 inline的优势：
 * 不需要进行函数的实际调用
 * 附加可以提前整个函数中退出的功能，return
 */
inline fun printStringInline(str:String, block:(String)->Unit){
    println("printString begin")
    block(str)
    println("pringString end")
}

fun printStringNoInline(str:String,block:(String)->Unit){
    println("printString begin")
    block(str)
    println("pringString end")
}

inline fun printStringInline2(str:String, block:(String)->Unit){
    println("printString begin")
    block(str)
    println("pringString end")
}


inline fun printStringInline3(str:String, block:(String)->Unit){
    println("printString begin")
    //inline的局限性一，inline函数中只能调用inline函数，不能调用noinline函数
    // 解决方法是，用noinline关键字包装，例如printStringInline3_2，同时失去闭包的内联能力
    //printStringNoInline(str,block)
    printStringInline2(str,block)
    println("pringString end")
}

inline fun printStringInline3_2(str:String, noinline block:(String)->Unit){
    println("printString begin")
    //inline的局限性
    printStringNoInline(str,block)
    println("pringString end")
}

inline fun printStringInline4(str:String, block:(String)->Unit){
    println("printString begin")
    //inline的局限性二，inline函数中不能将闭包block放在另外一个闭包runnable里面，因为没人清楚闭包runnable什么时候会触发
    // 解决方法是，用crossinline关键字包装，例如printStringInline4_2，同时会失去提前return全局函数的能力
    /*
    var runnable = Runnable {
        block(str)
    }
     */
    println("pringString end")
}

inline fun printStringInline4_2(str:String, crossinline block:(String)->Unit){
    println("printString begin")
    var runnable = Runnable {
        block(str)
    }
    println("pringString end")
}


fun inline_test1(){
    var str = ""
    println("init test1 begin")
    printStringInline(str){
        println("lambda start")
        if( str.isEmpty()){
            //这里的做法相当诡异，与普通语言不同
            //在闭包中return不是结束闭包，而是结束整个inline_test1
            return
        }
        println("lambda end")
    }
    println("init test1 end")
}

fun inline_test2(){
    var str = ""
    println("init test2 begin")
    printStringInline(str){
        println("lambda start")
        if( str.isEmpty()){
            //这里的return才是结束闭包
            return@printStringInline
        }
        println("lambda end")
    }
    println("init test2 end")
}

fun no_inline_test3(){
    var str = ""
    println("init test3 begin")
    printStringNoInline(str){
        println("lambda start")
        if( str.isEmpty()){
            //非inline函数，不能实现提前结束no_inline_test3
            //return
        }
        println("lambda end")
    }
    println("init test3 end")
}

fun no_inline_test4(){
    var str = ""
    println("init test4 begin")
    printStringInline4_2(str){
        println("lambda start")
        if( str.isEmpty()){
            //结束闭包
            return@printStringInline4_2
            //不能使用return，虽然是inline，因为有crossinline关键字
            //return
        }
        println("lambda end")
    }
    println("init test4 end")
}

fun crossinline_test5(){
    var str = ""
    println("init test4 begin")
    printStringNoInline(str){
        println("lambda start")
        if( str.isEmpty()){
            //结束闭包
            return@printStringNoInline
        }
        println("lambda end")
    }
    println("init test4 end")
}

fun InlineFunctionTest_Go(){
    inline_test1()
    inline_test2()
    no_inline_test3()
    no_inline_test4()
    crossinline_test5()
}
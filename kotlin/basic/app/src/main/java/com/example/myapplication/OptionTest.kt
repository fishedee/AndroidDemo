package com.example.myapplication

class Person(var name:String,var age:Int){

}

//参数类型为Person的时候，就是排除了null的这种情况
fun go1(a:Person){
    println("person.name = ${a.name}")
}

//参数类型为Person?的时候，才能包含有null的这种情况
fun go2(a:Person?){
    //这种情况下，需要先判断null，再获取字段
    if( a != null){
        println("person.name2 = ${a.name}")
    }

    //kotlin有省事的?判断操作符
    println("person.name2 = ${a?.name}")
}

fun go3(a:Person?){
    //这种情况下，需要先判断null，再获取字段，否则取默认值
    var result = "";
    if( a != null){
        result = a.name;
    }else{
        result = "[null]";
    }
    println("person.name3 = ${result}")

    //kotlin有省事的?判断操作符，再结合省事的?:二元操作符来表达
    println("person.name3 = ${a?.name?:"[null]"}")
}

fun go4(a:Person?){
    //!!是一个危险的操作，强行指定这个变量是非空的，出了问题由开发者自己兜底
    println("person.name4 = ${a!!.name}")
}
fun OptionTest_Go(){
    //编译不通过
    //go1(null)
    go1(Person("fish",123))

    go2(null)
    go2(Person("fish",123))


    go3(null)
    go3(Person("fish",123))

    go4(Person("fish",123))
    go4(null)//这里能编译通过，但是运行时抛出NullPointerException

}
package com.example.myapplication

//默认是不可以被继承的
class Person {

    //默认成员为public，自动生成对应的getter与setter
    var name = ""
    var age = 0

    //私有化setter
    var height:Float = 0f
        private set

    //自定义getter与setter
    var color = ""
        get(){
            println("color getter run")
            return field
        }
        set(value) {
            println("color setter run")
            field = value
        }

    fun eat(){
        println(name +" is eating. He is "+ age +" years old")
    }

    fun setHeight(height:Float){
        this.height = height
    }
}

fun ClassTest_Go(){
    val p = Person()
    p.age = 121
    p.name = "Fish"
    //失败，因为setter被设置为private
    //p.height = "123"
    //必须通过指定方法
    p.setHeight(123f)
    p.color = "blue"
    println("age = ${p.age} and name = ${p.name} and height = ${p.height} and color = ${p.color}")
    p.eat()
}
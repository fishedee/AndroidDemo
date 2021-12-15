package com.example.myapplication

//我们声明为可继承的类
open class QAnimal(protected var name:String){
    fun callName(){
        println("call ${this.name}")
    }

    fun walk(){
        println("walk")
    }
}

//QDog可以通过继承的方式，复用QAnimal的行为逻辑
class QDog:QAnimal("Dog"){
    fun run(){
        println("Dog run")

        //继承的问题在于，强约束
        //子类可以修改父类的变量，而且每个类只能有一个父类
        this.name = "xxx"
    }
}

//QAnimal2是不可被继承的
class QAnimal2(var name:String){
    fun callName(){
        println("call ${this.name}")
    }

    fun walk(){
        println("walk")
    }
}

//QDog2复用QAnimal2的另外一种方式是，组合，创建一个内部的Animal类
//然后将QAnimal2的接口手动复制过来，转发给QAnimal2
class QDog2{
    private val myAnimal:QAnimal2 = QAnimal2("Dog2")

    fun callName(){
        myAnimal.callName()
    }

    fun walk(){
        myAnimal.walk()
    }

    fun run(){
        println("Dog run")

        //组合更加灵活，我们可以只转发一部分接口
        //另外，一个类允许组合多个类的行为，没有继承带来的强约束问题
        //使用这种方法的麻烦在于，当QAnimal2后续新增了行为以后，QDog2都要手动添加对应接口来转发，比较麻烦
        //this.name = "xxx"
    }
}

interface QAnimal3Interface{
    fun callName()
    fun walk()
}

//QAnimal3是不可被继承的
class QAnimal3(var name:String):QAnimal3Interface{
    override fun callName(){
        println("call ${this.name}")
    }

    override fun walk(){
        println("walk")
    }
}

//QDog3使用委托机制来实现组合的灵活性，同时避免组合需要手动添加接口的麻烦。
class QDog3:QAnimal3Interface by QAnimal3("Dog3") {

    fun run(){
        println("Dog run")

        //这个时候，by委托当Dog的接口新增的时候，不需要更改。
    }
}



fun ByClassTest_Go(){
    val dog1 = QDog()
    dog1.walk()
    dog1.run()
    dog1.callName()

    val dog2 = QDog2()
    dog2.walk()
    dog2.run()
    dog2.callName()

    val dog3 = QDog3()
    dog3.walk()
    dog3.run()
    dog3.callName()
}
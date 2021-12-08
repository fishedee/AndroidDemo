package com.example.myapplication

class Animal(private var name:String){
    var nameLength:Int;

    init{
        //类实例时初始化的方法
        nameLength = name.length;
    }

    fun show(){
        println("name ${name} nameLength ${nameLength}")
    }
}
fun ClassInitTest_Go(){
    val animal = Animal("Dog");
    animal.show()
}
package com.example.myapplication

interface Flyer{
    fun fly()
}

class Bird:Flyer{
    override fun fly(){
        println("brid fly")
    }
}

fun InterfaceTest_Go(){
    val bird = Bird()
    bird.fly()
}
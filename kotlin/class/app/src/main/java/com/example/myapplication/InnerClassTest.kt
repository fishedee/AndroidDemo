package com.example.myapplication

class Garage(val address:String) {
    inner class Car(var name:String){
        fun getGarageAddress():String{
            return address
        }
    }

    fun createCar():Car{
        return Car("fish")
    }
}

class Garage2(val address:String) {
    class Car2(var name:String){
        fun getGarageAddress():String{
            //非内部类无法获取外部类的成员
            //return address
            return ""
        }
    }

    fun createCar():Car2{
        return Car2("fish")
    }
}

fun InnerClassTest_Go(){
    val garage = Garage("a")
    val car1 = garage.createCar()
    println(car1.getGarageAddress())

    //内部类无法在外部类之外创建，因为它需要获取外部类的引用
    //var car1_out = Garage.Car("kk")
    //内部类，需要用外部类引用才能创建，看一下和上一个语句区别
    var car1_out = garage.Car("fish")

    var garage2 = Garage2("b")
    var car2 = garage2.createCar()

    //无法获取address，因为是普通的嵌套类
    println(car2.getGarageAddress())

    //嵌套类，可以仅仅通过外部类名称就能创建
    var car2_out = Garage2.Car2("kk")
    //嵌套类，无需用外部类引用来创建
    //var car3_out = garage2.Car2("fish")
}
package com.example.myapplication

class Money(val value:Int) {
    operator fun plus(money:Money):Money{
        val sum = this.value + money.value
        return Money(sum)
    }

    operator fun plus(money:Int):Money{
        val sum = this.value + money
        return Money(sum)
    }

    operator fun minus(money:Money):Money{
        val sum = this.value - money.value
        return Money(sum)
    }

    operator fun minus(money:Int):Money{
        val sum = this.value - money
        return Money(sum)
    }

    operator fun times(money:Money):Money{
        val sum = this.value * money.value
        return Money(sum)
    }

    operator fun times(money:Int):Money{
        val sum = this.value * money
        return Money(sum)
    }

    operator fun div(money:Money):Money{
        val sum = this.value / money.value
        return Money(sum)
    }

    operator fun div(money:Int):Money{
        val sum = this.value / money
        return Money(sum)
    }

    operator fun compareTo(money:Money):Int{
        return this.value - money.value
    }

    override fun toString():String{
        return this.value.toString()
    }
}

fun MoneyTest(){
    val money1 = Money(10)
    val money2 = Money(20)
    var money3 = Money(10)
    println("money1 + money2 = ${money1+money2}")
    println("money1 - money2 = ${money1-money2}")
    println("money1 * money2 = ${money1*money2}")
    println("money1 / money2 = ${money1/money2}")
    println("money1 > money2 = ${money1 > money2}")
    println("money1 < money2 = ${money1 < money2}")
    println("money1 == money2 = ${money1 == money2}")
    println("money1 == money3 = ${money1 == money3}")


    println("money1 + 20 = ${money1+20}")
    println("money1 - 20 = ${money1-20}")
    println("money1 * 20 = ${money1*20}")
    println("money1 / 20 = ${money1/20}")
}

class MyArray {
    val list:ArrayList<Int> = ArrayList()

    fun add(data:Int){
        this.list.add(data)
    }

    operator fun get(index:Int):Int{
        return this.list.get(index)
    }

    operator fun set(index:Int,data:Int){
        this.list.set(index,data)
    }

    operator fun contains(data:Int):Boolean{
        return this.list.contains(data)
    }
}

fun MyArrayList_Go(){
    val list = MyArray()
    list.add(1)
    list.add(3)
    list.add(5)
    println("list[1] ${list[1]}")
    list[0] = 3
    println("list[0] ${list[0]}")
    println("3 in list is ${3 in list}")
    println("2 in list is ${2 in list}")
}

fun OperatorTest_Go(){
    MoneyTest()
    MyArrayList_Go()

    //返回值是IntRange类型
    //左闭右闭区间
    val target = (1..20)
    println("IntRange ${target}")
    val random = target.random()
    println("IntRange Random random ${random}")
    val newStr = "fish".repeat( random)
    println("newStr ${newStr}")
}
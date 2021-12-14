package com.example.myapplication

//显然Dog是Animal的子类
open class Animal(val name:String)

class Dog(name:String):Animal(name){}

//一个简单的泛型
class SimpleGeneric<T>(val data:T?){
    fun get():T?{
        return data
    }
}

fun TestError(){
    val animalTpl:SimpleGeneric<Animal>
    val dogTpl:SimpleGeneric<Dog> = SimpleGeneric(Dog("dog1"))

    //Dog是Animal的子类
    //但是SimpleGeneric<Dog>并不是SimpleGeneric<Animal>的子类，无法赋值
    //以下这句会报错，因为泛型包装的新类型，与原类型的关系，并不是简单的问题
    //animalTpl = dogTpl
}

//协变的泛型
class SimpleGeneric2<out T>(val data:T?){
    fun get():T?{
        return data
    }
}

fun TestCovariantGeneric(){
    val animalTpl:SimpleGeneric2<Animal>
    val dogTpl:SimpleGeneric2<Dog> = SimpleGeneric2(Dog("dog1"))

    //Dog是Animal的子类
    //当SimpleGeneric类型是协变的时候，泛型只能在函数的out位置，而不是in位置的时候。
    //SimpleGeneric<Dog>才是SimpleGeneric<Animal>的子类
    //所以，这句才会正确，编译不报错
    animalTpl = dogTpl
    println("animalTpl ${animalTpl}")

    //因为，能返回Animal类型的地方，肯定能返回Dog类型
}


//逆变的泛型
class SimpleGeneric3<in T>{
    fun set(a:T?):String{
        return a.toString()
    }
}

fun TestInvertvariantGeneric(){
    val animalTpl:SimpleGeneric3<Animal> = SimpleGeneric3()
    val dogTpl:SimpleGeneric3<Dog>

    //Dog是Animal的子类
    //当SimpleGeneric类型是逆变的时候，泛型只能在函数的in位置，而不是out位置的时候。
    //反过来，SimpleGeneric<Animal>是SimpleGeneric<Dog>的子类
    //所以，这句才会正确，编译不报错
    dogTpl = animalTpl
    println("animalTpl ${animalTpl}")

    //因为，能接收Dog参数的地方，肯定能接收Animal参数
}


//协变，与部分指定的泛型
class SimpleGeneric4<out T>(val data:T?){
    //@UnsafeVariance是特殊做法，指定不参与协变运算，因为这个方法仅仅用来查询，而不是设置进去的
    fun contains(a: @UnsafeVariance T?):Boolean{
        return true
    }
    fun get():T?{
        return data
    }
}


fun TestCovariantGeneric2(){
    val animalTpl:SimpleGeneric4<Animal>
    val dogTpl:SimpleGeneric4<Dog> = SimpleGeneric4(Dog("dog1"))

    animalTpl = dogTpl
    println("animalTpl ${animalTpl}")
}

fun CovariantGenericTest_Go(){
    TestError()
    TestCovariantGeneric()
    TestInvertvariantGeneric()
    TestCovariantGeneric2()
}


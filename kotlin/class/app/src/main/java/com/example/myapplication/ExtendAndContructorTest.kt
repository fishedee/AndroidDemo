package com.example.myapplication

//案例1
//要想让类继承，必须有open关键字
//无参数的Person2，默认有一个空参数的主构造函数
open class Person2{

}

//Student也没有参数，所以它也有一个空参数的主构造函数
//因此必须显式地调用Person2的空参数主构造函数
class Student2: Person2(){

}

//案例2
//主构造函数上的参数，默认会成为类的成员变量。
//而且默认这些成员为final，不能被覆写
open class Person3(val name:String,val age:Int){
    fun show(){
        println("name ${this.name} age ${this.age}")
    }
}

//Student有一个主构造函数，需要显式地调用父类的主构造函数
class Student3(val clazz:String, name2:String, age2:Int): Person3(name2,age2){
    fun showClazz(){
        println("clazz ${this.clazz}");
        //没有name2的成员，因为主构造函数上面没有val，或者var注解
        //println("name: ${this.name2} age: ${this.age2}")
    }
}

//案例3
// 可以被覆写的成员，需要加入open关键字
open class Person4( open val name:String,open val age:Int){
    fun show(){
        println("name ${this.name} age ${this.age}")
    }
}

//对于覆写的成员，也需要指定override关键字
class Student4(val clazz:String, override val name:String, override val age:Int): Person4(name,age){
    fun showClazz(){
        println("clazz ${this.clazz} name: ${this.name} age: ${this.age}")
    }
}

//案例4
//Person5有主构造函数
open class Person5( val name:String, val age:Int){
    fun show(){
        println("name ${this.name} age ${this.age}")
    }
}

//Student5有主构造函数，也有次构造函数
//每个类仅仅只能有一个主构造函数，次构造函数可以是多个
//对于每个次构造函数，它们都需要显式地调用自身的主构造函数
//次构造函数上面的参数不会成为成员。
class Student5(val clazz:String,val name2:String, val age2:Int): Person5(name2,age2){
    constructor(name:String,age:Int):this("go",name,age){

    }

    constructor(age:Int):this("go","fish",age){

    }

    fun showClazz(){
        println("clazz ${this.clazz} name: ${this.name} age: ${this.age}")
    }
}

//案例5
//Person6有主构造函数
open class Person6( val name:String, val age:Int){
    fun show(){
        println("name ${this.name} age ${this.age}")
    }
}

//相当特殊的情况
//Student6没有主构造函数，只有次构造函数的时候，需要显式地调用父类的主构造函数
//这个时候省略了括号
class Student6: Person6{
    var clazz:String = ""

    constructor(clazz:String,name:String,age:Int):super(name,age){
        this.clazz = clazz
    }

    constructor(clazz:String,age:Int):super("fish",age){
        this.clazz = clazz
    }

    fun showClazz(){
        println("clazz ${this.clazz} name ${this.name} age ${this.age}")
    }
}

//案例6
//Person7有主构造函数，没有次构造函数
open class Person7{
    var name:String = "";
    var age:Int = 0;
    constructor( name:String, age:Int){
        this.name = name;
        this.age =age;
    }
    constructor( age:Int){
        this.name = "fish2";
        this.age =age;
    }
    fun show(){
        println("name ${this.name} age ${this.age}")
    }
}

//相当特殊的情况
//Student7没有主构造函数
class Student7: Person7{
    var clazz:String = ""

    constructor(clazz:String,name:String,age:Int):super(name,age){
        this.clazz = clazz
    }

    constructor(clazz:String,age:Int):super(age){
        this.clazz = clazz
    }

    fun showClazz(){
        println("clazz ${this.clazz} name ${this.name} age ${this.age}")
    }
}

fun ExtendAndConstructorTest_Go(){
    val student2 = Student2();

    val student3 = Student3("student_clazz3","fish3",123);
    student3.show();
    student3.showClazz();

    val student4 = Student4("student_clazz4","fish4",124);
    student4.show();
    student4.showClazz();

    val student5 = Student5("fish5",125);
    student5.show();
    student5.showClazz();

    val student5_2 = Student5("fish5",25);
    student5_2.show();
    student5_2.showClazz();

    val student6 = Student6("student_clazz6",1);
    student6.show();
    student6.showClazz();

    val student7 = Student7("student_clazz7",1);
    student7.show();
    student7.showClazz();

    //总结来说，是这样的：
    //* Kotlin的主构造函数除了构造效果以外，还有自动赋值到成员变量的能力（需要加上var，或者val注解）
    //* Kotlin的次构造函数，没有自动赋值到成员变量的能力
    //* Kotlin的主构造函数存在时，次构造函数都需要用this方法来调用自身的主构造函数
    //* Kotlin的主构造函数不存在时，次构造函数都需要用this方法来调用父类的主或者次构造函数，这时候继承时没有括号，因为自身没有主构造函数
    //* Kotlin的成员变量默认为final的，需要显式地用open关键字来指定。子类覆写成员的时候，也需要显式的override关键字
}


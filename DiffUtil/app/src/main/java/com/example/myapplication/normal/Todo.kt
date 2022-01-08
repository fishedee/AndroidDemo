package com.example.myapplication.normal

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Todo(val id:Int,var name:String):Parcelable

@Parcelize
data class TodoDataContainer(val data:List<Todo>):Parcelable

fun parcelCopy(input:List<Todo>):List<Todo>{
    //序列化
    val oldData = TodoDataContainer(data=input)
    val parcel = Parcel.obtain()
    oldData.writeToParcel(parcel,0)
    parcel.setDataPosition(0)

    //反序列化
    val clazz = TodoDataContainer::class.java
    val clazz2 = clazz.classLoader.loadClass("${clazz.name}\$Creator")
    val instance = clazz2.newInstance()
    val method = clazz2.getMethod("createFromParcel",Parcel::class.java)
    val newData = method.invoke(instance,parcel) as TodoDataContainer
    return newData.data
}
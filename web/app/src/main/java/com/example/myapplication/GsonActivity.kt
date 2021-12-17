package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

data class Person(val name:String,val age:Int)

class GsonActivity : AppCompatActivity() {

    val gson:Gson

    init{
        val gsonBuilder = GsonBuilder()
        gsonBuilder.serializeNulls()
        gson = gsonBuilder.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gson)
        testGson()
    }

    private fun testGsonDeseiralize(){
        val data = "[{\"name\":\"Tom\",\"age\":20},{\"name\":\"Jack\",\"age\":25},{\"name\":\"Lily\",\"age\":22}]"
        val typeOf = object: TypeToken<List<Person>>(){}.type
        val personList = gson.fromJson<List<Person>>(data,typeOf)
        Log.d("personList","list "+personList.toString())
    }

    private fun testGsonSerialize(){
        val list = mutableListOf<Person>()
        list.add( Person("fish",123))
        list.add( Person("cat",456))
        list.add( Person("dog",789))
        val jsonStr = gson.toJson(list)
        Log.d("personList","json "+jsonStr)
    }

    private fun testGson(){
        testGsonDeseiralize()
        testGsonSerialize()
    }
}
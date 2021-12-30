package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

data class Person(val name:String,val age:Int)

class GsonActivity : AppCompatActivity() {

    val gson:Gson

    init{
        val gsonBuilder = GsonBuilder()
            .serializeNulls()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")//对时间的序列化格式处理
        gson = gsonBuilder.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gson)
        testGson()
    }

    data class MyDataRaw(val code:Int,val data:JsonObject)

    private fun testRawJson(){
        val data = "{\"code\":123,\"data\":{\"name\":\"fish\",\"age\":123}}"
        val typeOf = object: TypeToken<MyDataRaw>(){}.type
        val dataRaw = gson.fromJson<MyDataRaw>(data,typeOf)
        Log.d("rawJson",dataRaw.toString())
        /*
        输出结果：
        MyDataRaw(code=123, data={"name":"fish","age":123})
         */
        val typeOf2 = object :TypeToken<Person>(){}.type
        val person = gson.fromJson<Person>(dataRaw.data,typeOf2)
        Log.d("rawJson2",person.toString())
        /*
        输出结果：
        Person(name=fish, age=123)
         */
    }

    data class MyDataNull(val name:String,val age:Int,val address:String)

    private fun testNullData(){
        val data = "{\"name\":\"fish2\",\"age\":567,\"address\":null}"
        val typeOf = object :TypeToken<MyDataNull>(){}.type
        val dataNull = gson.fromJson<MyDataNull>(data,typeOf)
        Log.d("nullJson",dataNull.toString())
        /*
        输出结果如下：
        MyDataNull(name=fish2, age=567, address=null)
         */
    }

    private fun testGsonDeseiralize(){
        val data = "[{\"name\":\"Tom\",\"age\":20},{\"name\":\"Jack\",\"age\":25},{\"name\":\"Lily\",\"age\":22}]"
        val typeOf = object: TypeToken<List<Person>>(){}.type
        val personList = gson.fromJson<List<Person>>(data,typeOf)
        Log.d("personList","list "+personList.toString())
        /*
        输出结果：
        list [Person(name=Tom, age=20), Person(name=Jack, age=25), Person(name=Lily, age=22)]
         */
    }

    private fun testGsonSerialize(){
        val list = mutableListOf<Person>()
        list.add( Person("fish",123))
        list.add( Person("cat",456))
        list.add( Person("dog",789))
        val jsonStr = gson.toJson(list)
        Log.d("personList","json "+jsonStr)
        /*
        输出结果：
        json [{"age":123,"name":"fish"},{"age":456,"name":"cat"},{"age":789,"name":"dog"}]
         */
    }

    private fun testGson(){
        testGsonDeseiralize()
        testGsonSerialize()
        testRawJson()
        testNullData()
    }
}
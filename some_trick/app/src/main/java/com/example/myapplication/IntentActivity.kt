package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_intent.*
import java.io.Serializable

class Person(val name:String,val age:Int):Serializable

class Country(val name:String,val personList:List<Person>):Serializable

class IntentActivity : AppCompatActivity() {
    val gson:Gson

    init{
        val gsonBuilder = GsonBuilder()
        gsonBuilder.serializeNulls()
        gsonBuilder.disableHtmlEscaping()
        gson = gsonBuilder.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
        var country = intent.getSerializableExtra("data") as Country
        show.text = gson.toJson(country)
    }
    companion object{
        fun actionStart(context:Context,country:Country){
            var intent = Intent(context,IntentActivity::class.java)
            intent.putExtra("data",country)
            context.startActivity(intent)
        }
    }
}
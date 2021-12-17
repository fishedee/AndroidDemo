package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.okhttp.OkHttpService
import com.example.myapplication.service.Country
import com.example.myapplication.service.Result
import com.example.myapplication.service.RetrofitService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_ok_http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.lang.Exception

class OkHttpActivity : AppCompatActivity() {
    val gson: Gson

    init{
        val gsonBuilder = GsonBuilder()
        gsonBuilder.serializeNulls()
        gsonBuilder.disableHtmlEscaping()
        gson = gsonBuilder.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)

        testGo.setOnClickListener {
            //OkHttp不会自动跳转到UI线程
            OkHttpService.get1().enqueue(object: Callback {
                override fun onResponse(
                    call: Call,
                    response: Response
                ) {
                    val data= response.body?.string()
                    runOnUiThread {
                        showText.text =data
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        showText.text = "failure! " + e.message
                    }
                }
            })
        }

        //协程写法
        testGet.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                try{
                    val data = OkHttpService.get2("dog","mk")
                    showText.text = gson.toJson(data)
                }catch(e: Exception){
                    showText.text = "failure! "+ e.message
                }
            }
        }

        //协程写法
        testPost.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                try{
                    val country = Country("China", listOf(
                        Country.Person("sheep",123),
                        Country.Person("rat",456)))
                    val data = OkHttpService.post1(country,"m2")
                    showText.text = gson.toJson(data)
                }catch(e: Exception){
                    showText.text = "failure! "+ e.message
                }
            }
        }
    }
}
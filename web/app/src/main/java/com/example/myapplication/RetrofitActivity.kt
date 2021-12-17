package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.service.Country
import com.example.myapplication.service.Result
import com.example.myapplication.service.RetrofitService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_retrofit.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import java.lang.Exception

class RetrofitActivity : AppCompatActivity() {
    val gson:Gson
    init{
        val gsonBuilder = GsonBuilder()
        gsonBuilder.serializeNulls()
        gsonBuilder.disableHtmlEscaping()
        gson = gsonBuilder.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
        testGo.setOnClickListener {
            RetrofitService.appService.get1().enqueue(object:Callback<Result<String?>>{
                override fun onResponse(
                    call: Call<Result<String?>>,
                    response: Response<Result<String?>>
                ) {
                    val data= response.body()
                    showText.text = gson.toJson(data)
                }

                override fun onFailure(call: Call<Result<String?>>, t: Throwable) {
                    showText.text ="failure! "+t.message
                }
            })
        }

        //协程写法
        testGet.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                try{
                    val data = RetrofitService.appService.get2("fish","mm").await()
                    showText.text = gson.toJson(data)
                }catch(e:Exception){
                    showText.text = "failure! "+ e.message
                }
            }
        }

        //协程写法
        testPost.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                try{
                    val country = Country("China", listOf(Country.Person("fish",123),Country.Person("cat",456)))
                    val data = RetrofitService.appService.post1(country,"m2").await()
                    showText.text = gson.toJson(data)
                }catch(e:Exception){
                    showText.text = "failure! "+ e.message
                }
            }
        }

    }
}
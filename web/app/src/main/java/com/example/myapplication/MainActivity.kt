package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webViewTest.setOnClickListener {
            var intent = Intent(this,WebviewActivity::class.java)
            startActivity(intent)
        }
        gsonTest.setOnClickListener {
            var intent = Intent(this,GsonActivity::class.java)
            startActivity(intent)
        }
        okHttpTest.setOnClickListener {
            var intent = Intent(this,OkHttpActivity::class.java)
            startActivity(intent)
        }
        retrofitTest.setOnClickListener {
            var intent = Intent(this,RetrofitActivity::class.java)
            startActivity(intent)
        }
    }

}
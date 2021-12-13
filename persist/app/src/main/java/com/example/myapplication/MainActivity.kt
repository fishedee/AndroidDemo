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

        fileTest.setOnClickListener {
            var intent = Intent(this,FileActivity::class.java)
            startActivity(intent)
        }
        sqlTest.setOnClickListener {
            var intent = Intent(this,SQLiteActivity::class.java)
            startActivity(intent)
        }
    }

}
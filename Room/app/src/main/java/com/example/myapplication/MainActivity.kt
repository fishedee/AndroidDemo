package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        liveDataTest.setOnClickListener {
            var intent = Intent(this,RoomActivity::class.java)
            startActivity(intent)
        }
        liveDataTest2.setOnClickListener {
            var intent = Intent(this,RoomActivity2::class.java)
            startActivity(intent)
        }
    }

}
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
        callback_event_test.setOnClickListener {
            var intent = Intent(this,CallbackEventActivity::class.java)
            startActivity(intent)
        }
        bubble_event_test.setOnClickListener {
            var intent = Intent(this,BubbleEventActivity::class.java)
            startActivity(intent)
        }
        handler_event_test.setOnClickListener {
            var intent = Intent(this,HandlerEventActivity::class.java)
            startActivity(intent)
        }
    }

}
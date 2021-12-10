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
        linear_test.setOnClickListener {
            var intent = Intent(this,LinearLayoutActivity::class.java)
            startActivity(intent)
        }
        relative_test.setOnClickListener {
            var intent = Intent(this,RelativeLayoutActivity::class.java)
            startActivity(intent)
        }
        frame_test.setOnClickListener {
            var intent = Intent(this,FrameLayoutActivity::class.java)
            startActivity(intent)
        }
    }

}
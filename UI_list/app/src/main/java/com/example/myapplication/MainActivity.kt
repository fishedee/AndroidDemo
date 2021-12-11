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

        button1.setOnClickListener {
            var intent = Intent(this,ListViewAcitvity::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            var intent = Intent(this,RecycleViewActivity::class.java)
            startActivity(intent)
        }
        button3.setOnClickListener {
            var intent = Intent(this,RecycleViewActivity2::class.java)
            startActivity(intent)
        }
    }

}
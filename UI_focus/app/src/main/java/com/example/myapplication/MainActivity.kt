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
        focus_default.setOnClickListener {
            var intent = Intent(this,DefaultFocusActivity::class.java)
            startActivity(intent)
        }
        focus_focusable.setOnClickListener {
            var intent = Intent(this,FocusableActivity::class.java)
            startActivity(intent)
        }
        focus_descent.setOnClickListener {
            var intent = Intent(this,DescendantFocusActivity::class.java)
            startActivity(intent)
        }
    }

}
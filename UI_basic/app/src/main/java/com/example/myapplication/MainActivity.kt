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
        text_view_test.setOnClickListener {
            var intent = Intent(this,TextActivity::class.java)
            startActivity(intent)
        }
        button_test.setOnClickListener {
            var intent = Intent(this,ButtonActivity::class.java)
            startActivity(intent)
        }
        edit_text_text.setOnClickListener {
            var intent = Intent(this,EditTextActivity::class.java)
            startActivity(intent)
        }
        image_view_test.setOnClickListener {
            var intent = Intent(this,ImageViewActivity::class.java)
            startActivity(intent)
        }
        progress_test.setOnClickListener {
            var intent = Intent(this,ProgressBarActivity::class.java)
            startActivity(intent)
        }
    }

}
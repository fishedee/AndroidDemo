package com.example.myapplication.explicit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class ExplicitFirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_first)
        button1.setOnClickListener {
            //通过直接指定Activity类名的方式来启动Intent
            var intent = Intent(this,ExplicitSecondActivity::class.java);
            startActivity(intent)
        }
    }
}
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
            var intent = Intent(this,DialogActivity::class.java);
            startActivity(intent)
        }
        Log.d("lifecycle","onCreate");
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle","onStart");
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle","onResume");
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle","onPause");
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle","onStop");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle","onDestroy");
    }
}
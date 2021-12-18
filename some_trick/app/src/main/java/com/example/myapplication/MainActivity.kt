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
        toastTest.setOnClickListener {
            showToast("Hello")
        }
        intentTest.setOnClickListener {
            val country = Country("China", listOf(Person("fish",123),Person("cat",456)))
            IntentActivity.actionStart(this,country)
        }
    }

}
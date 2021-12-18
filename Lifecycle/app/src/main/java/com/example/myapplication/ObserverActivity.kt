package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ObserverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observer)
        lifecycle.addObserver(MyObserver())
    }
}
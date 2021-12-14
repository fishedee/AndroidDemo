package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notifyTest.setOnClickListener{
            val intent = Intent(this,NotifyActivity::class.java)
            startActivity(intent)
        }
        cameraTest.setOnClickListener {
            var intent = Intent(this,CameraActivity::class.java)
            startActivity(intent)
        }
        albumTest.setOnClickListener {
            var intent = Intent(this,AlbumActivity::class.java)
            startActivity(intent)
        }
    }

}
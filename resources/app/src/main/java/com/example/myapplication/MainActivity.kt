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
        value_resource_test.setOnClickListener {
            var intent = Intent(this,ValueResourceActivity::class.java)
            startActivity(intent)
        }
        property_animator_test.setOnClickListener {
            var intent = Intent(this,PropertyAnimationActivity::class.java)
            startActivity(intent)
        }
        tween_animator_test.setOnClickListener {
            var intent = Intent(this,TweenAnimationActivity::class.java)
            startActivity(intent)
        }
        frame_animator_test.setOnClickListener {
            var intent = Intent(this,FrameAnimationActivity::class.java)
            startActivity(intent)
        }
        style_test.setOnClickListener {
            var intent = Intent(this,StyleAndThemeActivity::class.java)
            startActivity(intent)
        }
        raw_test.setOnClickListener {
            var intent = Intent(this,RawActivity::class.java)
            startActivity(intent)
        }
    }

}
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
        shape_test.setOnClickListener {
            var intent = Intent(this,ShapeActivity::class.java)
            startActivity(intent)
        }
        state_list_test.setOnClickListener {
            var intent = Intent(this,StateListActivity::class.java)
            startActivity(intent)
        }
        layer_test.setOnClickListener {
            var intent = Intent(this,LayerableActivity::class.java)
            startActivity(intent)
        }
        clip_test.setOnClickListener {
            var intent = Intent(this,ClipDrawable::class.java)
            startActivity(intent)
        }
    }

}
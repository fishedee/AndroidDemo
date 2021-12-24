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

        recycler_view_1.setOnClickListener {
            var intent = Intent(this,RecyclerViewActivityWithHeader::class.java)
            startActivity(intent)
        }

        recycler_view_2.setOnClickListener {
            var intent = Intent(this,RecyclerViewActivityWithDivider::class.java)
            startActivity(intent)
        }
    }

}
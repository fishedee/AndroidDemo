package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.fragment.FragmentActivity
import com.example.myapplication.view.UserViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener {
            var intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            var intent = Intent(this, UserViewActivity::class.java)
            startActivity(intent)
        }
    }

}
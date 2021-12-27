package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.new_recommend.ViewModelActivity4
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel1.setOnClickListener {
            var intent = Intent(this,ViewModelActivity1::class.java)
            startActivity(intent)
        }

        viewModel2.setOnClickListener {
            var intent = Intent(this,ViewModelActivity2::class.java)
            startActivity(intent)
        }

        viewModel3.setOnClickListener {
            var intent = Intent(this,ViewModelActivity3::class.java)
            startActivity(intent)
        }

        viewModel4.setOnClickListener {
            var intent = Intent(this,ViewModelActivity4::class.java)
            startActivity(intent)
        }
    }

}
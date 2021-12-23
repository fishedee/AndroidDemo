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
        linear_test.setOnClickListener {
            var intent = Intent(this,LinearLayoutActivity::class.java)
            startActivity(intent)
        }
        linear_test2.setOnClickListener {
            var intent = Intent(this,LinearLayoutActivity2::class.java)
            startActivity(intent)
        }
        linear_test3.setOnClickListener {
            var intent = Intent(this,LinearLayoutActivity3::class.java)
            startActivity(intent)
        }
        relative_test.setOnClickListener {
            var intent = Intent(this,RelativeLayoutActivity::class.java)
            startActivity(intent)
        }
        frame_test.setOnClickListener {
            var intent = Intent(this,FrameLayoutActivity::class.java)
            startActivity(intent)
        }
        grid_layout_test.setOnClickListener {
            var intent = Intent(this,GridLayoutActivity::class.java)
            startActivity(intent)
        }
        constraint_layout_1.setOnClickListener {
            var intent = Intent(this,ConstraintLayoutActivity::class.java)
            startActivity(intent)
        }
        constraint_layout_2.setOnClickListener {
            var intent = Intent(this,ConstraintLayoutActivity2::class.java)
            startActivity(intent)
        }
        constraint_layout_3.setOnClickListener {
            var intent = Intent(this,ConstraintLayoutActivity3::class.java)
            startActivity(intent)
        }
        constraint_layout_4.setOnClickListener {
            var intent = Intent(this,ConstraintLayoutActivity4::class.java)
            startActivity(intent)
        }
    }

}
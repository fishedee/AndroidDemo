package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.best.BestActivity
import com.example.myapplication.explicit.ExplicitFirstActivity
import com.example.myapplication.explicit_result.ExplicitResultFirstActivity
import com.example.myapplication.implicit.ImplicitMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener {
            var intent = Intent(this,ExplicitFirstActivity::class.java);
            startActivity(intent)
        }
        button2.setOnClickListener {
            var intent = Intent(this,ExplicitResultFirstActivity::class.java)
            startActivity(intent)
        }
        button3.setOnClickListener {
            var intent = Intent(this,ImplicitMainActivity::class.java)
            startActivity(intent)
        }
        button4.setOnClickListener {

            var intent = Intent(this,BestActivity::class.java)
            startActivity(intent)
        }
    }
}
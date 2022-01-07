package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.diff.DiffOneActivity
import com.example.myapplication.diff2.DiffTwoActivity
import com.example.myapplication.normal.NormalActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.normalButton.setOnClickListener {
            var intent = Intent(this,NormalActivity::class.java)
            startActivity(intent)
        }
        viewBinding.diffOneButton.setOnClickListener {
            var intent = Intent(this,DiffOneActivity::class.java)
            startActivity(intent)
        }
        viewBinding.diffTwoButton.setOnClickListener {
            var intent = Intent(this,DiffTwoActivity::class.java)
            startActivity(intent)
        }
    }

}
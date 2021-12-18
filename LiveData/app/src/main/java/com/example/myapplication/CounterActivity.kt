package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_counter.*

class CounterActivity : AppCompatActivity() {
    lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        this.viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(CounterViewModel::class.java)

        this.viewModel.counter.observe(this){counter->
            showText.text = counter.toString()
        }

        this.viewModel.initData()

        inc.setOnClickListener {
            this.viewModel.plusOne()
        }
    }
}
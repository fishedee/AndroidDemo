package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_view_model1.*

class ViewModelActivity1 : AppCompatActivity() {
    lateinit var viewModel: MyViewModel1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model1)

        //创建无参数的ViewModel
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MyViewModel1::class.java)

        plusOne.setOnClickListener {
            viewModel.counter++
            showText.text = viewModel.counter.toString()
        }

        showText.text = viewModel.counter.toString()
    }
}
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_view_model1.*
import kotlinx.android.synthetic.main.activity_view_model2.*
import kotlinx.android.synthetic.main.activity_view_model2.plusOne
import kotlinx.android.synthetic.main.activity_view_model2.showText

class ViewModelActivity2 : AppCompatActivity() {
    private lateinit var viewModel:MyViewModel2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model2)


        //创建含参数的ViewModel
        viewModel = ViewModelProvider(this, MyViewModel2.Factory(100)).get(MyViewModel2::class.java)

        plusOne.setOnClickListener {
            viewModel.inc()
            showText.text = viewModel.get().toString()
        }

        showText.text = viewModel.get().toString()
    }
}
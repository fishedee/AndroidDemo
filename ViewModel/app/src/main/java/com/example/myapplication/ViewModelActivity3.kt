package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_view_model2.*
import kotlinx.android.synthetic.main.activity_view_model2.plusOne
import kotlinx.android.synthetic.main.activity_view_model2.showText
import kotlinx.android.synthetic.main.activity_view_model3.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ViewModelActivity3 : AppCompatActivity() {

    val mainScope = MainScope()

    lateinit var viewModel: MyViewModel3

    override fun onDestroy() {
        super.onDestroy()
        //退出的时候，记得cancel当前的scope
        mainScope.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model3)


        //创建无参数的ViewModel
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MyViewModel3::class.java)

        plusOne.setOnClickListener {
            mainScope.launch {
                Log.d("ViewModel","pre inc")
                viewModel.inc()
                Log.d("ViewModel","post inc ${viewModel.get()}")
                showText.text = viewModel.get().toString()
            }
        }
        cancel.setOnClickListener {
            mainScope.launch {
                viewModel.cancel()
            }
        }

        showText.text = viewModel.get().toString()
    }
}
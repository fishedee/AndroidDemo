package com.example.myapplication.new_recommend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.whenStarted
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_view_model4.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ViewModelActivity4 : AppCompatActivity() {

    //嵌入一个viewModel
    private val viewModel: MyViewModel4 by viewModels()

    init {
        lifecycleScope.launch {
            //当counter变化的时候，通知showText变化
            //为什么要用repeatOnLifecycle
            //因为我们希望UI在不可见的时候，不要进行更新，提高性能以及避免崩溃
            //repeatOnLifecycle(Lifecycle.State.STARTED)的意思:
            //每次Activity进入Started的时候就会执行，Activity退出Started的时候就会自动cancel
            //可重启生命周期感知型协程，注意这个函数是阻塞的，不会返回回来
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                Log.d("ViewModel4", "init collect")
                viewModel.counter.collect { v ->
                    Log.d("ViewModel4", "have data ${v}")
                    showText.text = v.toString()
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.effect.collect {
                    Toast.makeText(this@ViewModelActivity4,it,Toast.LENGTH_SHORT).show()
                }
            }
        }
        lifecycleScope.launch {
            //whenStarted，是进入Started状态的时候执行闭包代码，当退出Started的时候会自动挂起（不是取消）
            //当页面重新进入Started状态，恢复运行
            //挂起生命周期感知型协程
            whenStarted {
                delay(100)
                Log.d("ViewModel4","init started")
                viewModel.setInitValue(101)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model4)

        plusOne.setOnClickListener {
            viewModel.inc()
        }

        minusOne.setOnClickListener {
            viewModel.minus()
        }
    }
}
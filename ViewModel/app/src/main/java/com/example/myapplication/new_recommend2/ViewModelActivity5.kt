package com.example.myapplication.new_recommend2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_view_model4.*
import kotlinx.android.synthetic.main.activity_view_model5.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ViewModelActivity5 : AppCompatActivity() {

    val TAG = ViewModelActivity5::class.java.name

    //嵌入一个viewModel
    private val viewModel: MyViewModel5 by viewModels()

    init{
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                Log.i(TAG,"repeatOnLifecycle begin collect")
                viewModel.counterState.collect {
                    Log.i(TAG,"repeatOnLifecycle collect ${it}")
                    five_show_text2.text = it.toString();
                }
            }
        }

        //这里必须使用launchWhenCreated，使用launchWhenStarted会产生effect的堵塞问题
        lifecycleScope.launchWhenCreated {
            Log.i(TAG,"launchWhenCreated begin collect")
            viewModel.effect.collect {
                Log.i(TAG,"launchWhenCreated collect ${it}")
                five_show_text.text = it.toString();
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model5)

        five_auto_add.setOnClickListener {
            //使用launchWhenStarted会在跳转页面以后自动暂停，这可能造成数据设置延迟
            lifecycleScope.launchWhenStarted {
                for( i in 0..100){
                    delay(100);
                    Log.i(TAG,"inc $i")
                    viewModel.inc();
                }
            }
        }

        five_auto_add2.setOnClickListener {
            //使用launch不会自动暂停，只会在Activity退出的时候取消协程
            lifecycleScope.launch {
                for( i in 0..100){
                    delay(100);
                    Log.i(TAG,"inc2 $i")
                    viewModel.inc();
                }
            }
        }

        five_next_page.setOnClickListener {
            val intent = Intent(this,EmptyActivity5::class.java)
            startActivity(intent)
        }
    }
}
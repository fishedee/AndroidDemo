package com.example.myapplication.normal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityNormalBinding
import kotlinx.coroutines.flow.collect

class NormalActivity : AppCompatActivity() {
    private lateinit var viewBinding:ActivityNormalBinding

    private val viewModel:NormalViewModel by viewModels()

    init {
        lifecycleScope.launchWhenStarted {
            viewModel.listEffect.collect { v->
                adapter.setData(v.data)
                adapter.notifyDataSetChanged()
                v.effect(viewBinding.showList,adapter)
            }
        }
    }

    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityNormalBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //初始化数据
        val initData = viewModel.init(10000)

        //初始化ui
        adapter = TodoAdapter()
        adapter.setData(initData)
        adapter.modListener = {v->
            lifecycleScope.launchWhenStarted {
                viewModel.mod(v.id,"测试")
            }
        }
        adapter.delListener = {v->
            lifecycleScope.launchWhenStarted {
                viewModel.del(v.id)
            }
        }

        val layoutManager = LinearLayoutManager(this)
        //要多传入一个排序属性
        viewBinding.showList.layoutManager = layoutManager
        viewBinding.showList.adapter = adapter

        viewBinding.add.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                viewModel.add()
            }
        }
    }
}
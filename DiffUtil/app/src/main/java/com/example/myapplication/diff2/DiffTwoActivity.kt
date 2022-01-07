package com.example.myapplication.diff2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityDiffOneBinding
import com.example.myapplication.databinding.ActivityDiffTwoBinding
import com.example.myapplication.databinding.ActivityNormalBinding
import com.example.myapplication.normal.NormalViewModel
import com.example.myapplication.normal.TodoAdapter
import kotlinx.coroutines.flow.collect

class DiffTwoActivity : AppCompatActivity() {

    private lateinit var viewBinding:ActivityDiffTwoBinding

    private val viewModel: NormalViewModel by viewModels()

    init {
        lifecycleScope.launchWhenStarted {
            viewModel.listEffect.collect { v->
                //注意，仍然需要进行深拷贝操作
                val newData = v.data.map { v->v.copy() }
                adapter.submitList(newData,{
                    v.effect(viewBinding.showList,adapter)
                })

            }
        }
    }

    private lateinit var adapter: TodoDiffAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityDiffTwoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //初始化数据
        val initData = viewModel.init(10000)

        //初始化ui
        adapter = TodoDiffAdapter()
        adapter.submitList(initData.map { v->v.copy() })
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
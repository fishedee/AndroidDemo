package com.example.myapplication.diff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityDiffOneBinding
import com.example.myapplication.databinding.ActivityNormalBinding
import com.example.myapplication.normal.NormalViewModel
import com.example.myapplication.normal.TodoAdapter
import kotlinx.coroutines.flow.collect

class DiffOneActivity : AppCompatActivity() {

    private lateinit var viewBinding:ActivityDiffOneBinding

    private val viewModel: NormalViewModel by viewModels()

    init {
        lifecycleScope.launchWhenStarted {
            viewModel.listEffect.collect { v->
                val oldData = adapter.getData()
                //深拷贝以后，保证ViewModel的data数据，与adpater的data数据不是同一个实例
                val newData = v.data.map { v->v.copy() }
                val diffResult = DiffUtil.calculateDiff(TodoDiffCallback(oldData,newData))

                //写入到adapter
                adapter.setData(newData)
                diffResult.dispatchUpdatesTo(adapter)
                v.effect(viewBinding.showList,adapter)
            }
        }
    }

    private lateinit var adapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityDiffOneBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //初始化数据
        val initData = viewModel.init(10000)

        //初始化ui
        adapter = TodoAdapter()
        adapter.setData(initData.map { v->v.copy() })
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
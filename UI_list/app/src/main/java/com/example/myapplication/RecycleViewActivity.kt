package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list_view_acitvity.*
import kotlinx.android.synthetic.main.activity_list_view_acitvity.button_add
import kotlinx.android.synthetic.main.activity_list_view_acitvity.button_add2
import kotlinx.android.synthetic.main.activity_recycle_view.*

class RecycleViewActivity : AppCompatActivity() {
    private val todoList = ArrayList<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)

        initTodoList()
        //只需要实现一个Adapter就可以了
        val adapter = TodoAdapter2(todoList)
        val layoutManager = LinearLayoutManager(this)
        //要多传入一个排序属性
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        //recyclerView不再有onItemClick的

        button_add.setOnClickListener {
            val id = todoList.size+1;

            //修改todoList
            todoList.add(Todo("job_"+id,"fish_"+id))

            //需要手动通知Adapter发生了变化，否则不更新
            adapter.notifyDataSetChanged()
        }

        button_add2.setOnClickListener {
            val id = todoList.size+1;

            //修改todoList
            todoList.add(Todo("job_"+id,"fish_"+id))
            //因为Adapter里面不再存储数据本身，所以没有add操作
            //adapter.add(Todo("job_"+id,"fish_"+id))
            //比notifyDataSetChanged更有效率的更新通知方式
            adapter.notifyItemInserted(todoList.size-1)
        }
    }


    private fun initTodoList(){
        for( i in 0..1000){
            todoList.add(Todo("job_"+i,"fish_"+i))
        }
    }
}
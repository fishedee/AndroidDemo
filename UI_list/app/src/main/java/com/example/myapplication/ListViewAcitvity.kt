package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_view_acitvity.*

class ListViewAcitvity : AppCompatActivity() {
    private val todoList = ArrayList<Todo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_acitvity)

        initTodoList()
        //只需要实现一个Adapter就可以了
        val adapter = TodoAdapter(this,R.layout.todo_item,todoList)
        listView.adapter = adapter
        //ListView下面的含有Button的时候，要将Button设置focusable为false，才能响应onItemClickListener
        //看这里https://blog.csdn.net/yissan/article/details/50448950
        listView.setOnItemClickListener { _, _, position, _ ->
            Log.d("listView","click "+position)
            var todo = todoList[position]
            Toast.makeText(this,todo.title,Toast.LENGTH_SHORT).show()
        }

        button_add.setOnClickListener {
            val id = todoList.size+1;

            //修改todoList
            todoList.add(Todo("job_"+id,"fish_"+id))

            //需要手动通知Adapter发生了变化，否则不更新
            adapter.notifyDataSetChanged()
        }

        button_add2.setOnClickListener {
            val id = todoList.size+1;

            //使用Adapter来更新数据
            adapter.add(Todo("job_"+id,"fish_"+id))
        }
    }


    private fun initTodoList(){
        for( i in 0..1000){
            todoList.add(Todo("job_"+i,"fish_"+i))
        }
    }
}
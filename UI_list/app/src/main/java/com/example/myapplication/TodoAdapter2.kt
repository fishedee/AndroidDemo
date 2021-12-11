package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

//仅仅需要传递ViewHolder，连data都不需要传递，这大幅增加了data的灵活性
class TodoAdapter2(val todoList:List<Todo>):RecyclerView.Adapter<TodoAdapter2.ViewHolder>() {

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        var itemView:LinearLayout = view.findViewById(R.id.itemView)
        val title:TextView = view.findViewById(R.id.text)
        var del:Button = view.findViewById(R.id.del)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false)
        var viewHolder =  ViewHolder(view)
        //RecyclerView默认没有边框线，需要手动添加
        viewHolder.itemView.setBackgroundResource(R.drawable.border)
        //使用View点击的方式
        viewHolder.itemView.setOnClickListener {
            //取得当前ViewHolder的位置
            var position = viewHolder.adapterPosition
            var todo = todoList[position]
            Toast.makeText(parent.context,todo.title, Toast.LENGTH_SHORT).show()
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var todo = todoList[position]
        holder.title.setText(todo.title)
        holder.del.setText("["+todo.user+"]添加")
    }

    //返回Item数量
    override fun getItemCount() = todoList.size
}
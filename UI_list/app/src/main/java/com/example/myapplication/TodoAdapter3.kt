package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

//仅仅需要传递ViewHolder，连data都不需要传递，这大幅增加了data的灵活性
class TodoAdapter3(val todoList:List<Todo>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class NormalViewHolder(view: View):RecyclerView.ViewHolder(view){
        val title:TextView = view.findViewById(R.id.text)
        var del:Button = view.findViewById(R.id.del)
    }

    inner class EditViewHolder(view: View):RecyclerView.ViewHolder(view){
        val title:TextView = view.findViewById(R.id.text)
        var del:TextView = view.findViewById(R.id.del)
    }

    val NormalMsgType = 1;
    var EditMsgType = 2;

    override fun getItemViewType(position: Int): Int {
        val todo = todoList[position]
        if( todo.title.contains("1")){
            return NormalMsgType;
        }else{
            return EditMsgType;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder:RecyclerView.ViewHolder;
        if( viewType == NormalMsgType ){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false)
            viewHolder = NormalViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item2,parent,false)
            viewHolder = EditViewHolder(view)
        }
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var todo = todoList[position]
        when(holder){
            is NormalViewHolder->{
                holder.title.setText(todo.title)
                holder.del.setText("["+todo.user+"]添加")
            }
            is EditViewHolder->{
                holder.title.setText(todo.title)
                holder.del.setText("["+todo.user+"]添加")
            }
        }
    }

    //返回Item数量
    override fun getItemCount() = todoList.size
}
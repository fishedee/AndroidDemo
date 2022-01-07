package com.example.myapplication.normal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.WidgetItemTodoBinding

class TodoAdapter(): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    private var todoList:List<Todo> = ArrayList<Todo>()

    fun setData(data:List<Todo>){
        this.todoList = data
    }

    fun getData():List<Todo>{
        return this.todoList
    }

    var modListener:((todo:Todo)->Unit)? = null

    var delListener:((todo:Todo)->Unit)? = null

    inner class ViewHolder(val viewBinding: WidgetItemTodoBinding):RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = WidgetItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
                parent,
        false)
        var viewHolder =  ViewHolder(viewBinding)
        //使用View点击的方式
        viewHolder.itemView.setOnClickListener {
            //取得当前ViewHolder的位置
            var position = viewHolder.adapterPosition
            var todo = todoList[position]
            Toast.makeText(parent.context,todo.name, Toast.LENGTH_SHORT).show()
        }
        viewHolder.viewBinding.modButton.setOnClickListener {
            var position = viewHolder.adapterPosition
            var todo = todoList[position]
            this.modListener?.invoke(todo)
        }
        viewHolder.viewBinding.delButton.setOnClickListener {
            var position = viewHolder.adapterPosition
            var todo = todoList[position]
            this.delListener?.invoke(todo)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var todo = todoList[position]
        val viewBinding = holder.viewBinding
        viewBinding.id.text = todo.id.toString()
        viewBinding.name.text = todo.name
    }

    //返回Item数量
    override fun getItemCount() = todoList.size
}
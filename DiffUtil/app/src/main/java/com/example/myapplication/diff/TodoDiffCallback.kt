package com.example.myapplication.diff

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.normal.Todo


class TodoDiffCallback(val oldTodo:List<Todo>, val newTodo:List<Todo>): DiffUtil.Callback(){
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //检查内容
        return oldTodo[oldItemPosition] == newTodo[newItemPosition]
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        //检查id
        return oldTodo[oldItemPosition].id == newTodo[newItemPosition].id
    }

    override fun getNewListSize(): Int {
        return newTodo.size
    }

    override fun getOldListSize(): Int {
        return oldTodo.size
    }

}
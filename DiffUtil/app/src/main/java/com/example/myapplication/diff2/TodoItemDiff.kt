package com.example.myapplication.diff2

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.normal.Todo

class TodoItemDiff : DiffUtil.ItemCallback<Todo>() {
    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem.id == newItem.id
    }
}
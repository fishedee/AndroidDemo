package com.example.myapplication.util

import androidx.recyclerview.widget.RecyclerView

data class ListEvent<T>(val data:T,val effect:(view:RecyclerView,adapter:RecyclerView.Adapter<*>)->Unit)
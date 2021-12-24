package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_view_with_divider.*

class RecyclerViewActivityWithDivider : AppCompatActivity() {
    val data:List<String>

    init{
        val listData = ArrayList<String>()
        for( i in 0..100){
            listData.add(i.toString()+"_txt")
        }
        this.data = listData
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_with_divider)


        val linearLayoutManager = LinearLayoutManager(this)
        val adapter = DataAdapterWithHeader(this.data)
        val itemDecoration = LinearRecycleViewDecoration(this,LinearRecycleViewDecoration.VERTICAL_LIST,R.drawable.border)
        recycler_view.layoutManager = linearLayoutManager
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(itemDecoration)
    }
}
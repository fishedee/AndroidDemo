package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_view_with_header.*
import android.view.LayoutInflater

class RecyclerViewActivityWithHeader : AppCompatActivity() {
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
        setContentView(R.layout.activity_recycler_view_with_header)

        val linearLayoutManager = LinearLayoutManager(this)
        val adapter = DataAdapterWithHeader(this.data)
        recycler_view.layoutManager = linearLayoutManager
        recycler_view.adapter = adapter

        //设置Header与Footer
        val header = LayoutInflater.from(this).inflate(R.layout.cyclerview_header, recycler_view, false)
        adapter.setHeaderView(header)
        val footer = LayoutInflater.from(this).inflate(R.layout.cyclerview_footer, recycler_view, false)
        adapter.setFooterView(footer)
    }
}
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*

class ValueResourceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_resource)

        //代码的方式获取资源
        val bool = resources.getBoolean(R.bool.female)
        val color = resources.getColor(R.color.black)
        val str = resources.getString(R.string.str2)
        val dimen = resources.getDimension(R.dimen.cell_with)
        val array = resources.getStringArray(R.array.Books)
        val array2 = resources.obtainTypedArray(R.array.plain_arr)

        Log.d("ValueResource","Bool ${bool}")
        Log.d("ValueResource","Color ${color}")
        Log.d("ValueResource","String ${str}")
        Log.d("ValueResource","Dimen ${dimen}")
        Log.d("ValueResource","StringArray ${array.toList()}")
        //getColor，第一个参数是index索引，第二个参数是不存在时的默认值
        Log.d("ValueResource","TypedArray ${array2} ${array2.getColor(0,0)}")
    }
}
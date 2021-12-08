package com.example.myapplication.best

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

//用来记录所有的Activity，方便记录Activity栈，以及一键退出程序
open class BestBaseActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        Log.d("base create",this::class.java.simpleName)

        ActivityCollector.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("base destroy",this::class.java.simpleName)


        ActivityCollector.remove(this)
    }
}
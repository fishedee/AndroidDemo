package com.example.myapplication.best

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_best.*

class BestActivity : BestBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_best)

        var name = intent.getStringExtra("name")
        Log.d("BestActivity","input name = ${name}")

        button1.setOnClickListener {
            BestActivity.actionStart(this,"mm")
        }
        button2.setOnClickListener {
            ActivityCollector.finishAll()
        }
    }

    //伴生对象，相当于静态方法
    //使用伴生对象来启动其他的Activity
    companion object{
        fun actionStart(content:Context,name:String){
            var intent = Intent(content,BestActivity::class.java)
            intent.putExtra("name",name)
            content.startActivity(intent)
        }
    }
}
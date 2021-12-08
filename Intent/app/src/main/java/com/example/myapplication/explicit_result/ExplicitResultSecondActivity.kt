package com.example.myapplication.explicit_result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_explicit_result_second.*


class ExplicitResultSecondActivity : AppCompatActivity() {
    val logName = ExplicitResultSecondActivity::class.simpleName;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_result_second)

        //取出当前的intent，获取参数值
        var inputData = intent.getStringExtra("name");
        Log.d(logName,"input ${inputData}")

        button1.setOnClickListener {
            var intent = Intent()
            //设置返回值
            intent.putExtra("age","789")

            //RESULT_OK的值为-1
            //默认按下返回键退出的时候，返回值为RESULT_CANCELED，值为0
            setResult(RESULT_OK,intent)

            //退出当前的Activity
            finish()
        }
    }
}
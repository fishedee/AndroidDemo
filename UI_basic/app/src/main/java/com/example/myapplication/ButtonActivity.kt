package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_button.*

class ButtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)
        button1.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("This is dialog")
                setMessage("Something important")
                //不能点击灰白处关闭
                setCancelable(false)
                //闭包的两个参数
                setPositiveButton("确认"){dialog,which->}
                setNegativeButton("取消"){dialog,which->}
            }.show()
        }
    }
}
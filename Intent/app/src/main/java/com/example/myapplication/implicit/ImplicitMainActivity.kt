package com.example.myapplication.implicit

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_implicit_main.*

class ImplicitMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_main)
        button_web.setOnClickListener {
            //隐式intent的方式，先传入action
            var intent = Intent(Intent.ACTION_VIEW)
            //再填入category，category为default的时候可以不传入
            //intent.addCategory("android.intent.category.DEFAULT")

            //最后传入data，这个看不同的action再决定要不要传入
            intent.data = Uri.parse("https://www.baidu.com")

            //本地的WebActivity，与内置的浏览器都能响应这个Intent
            startActivity(intent)
        }

        button_tel.setOnClickListener {
            var intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }

        button_category.setOnClickListener {
            var intent = Intent("com.fishedee.ACTION_START")
            intent.addCategory("com.fishedee.MY_CATEGORY")
            startActivity(intent)
        }
    }
}
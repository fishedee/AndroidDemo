package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityViewBinding1Binding

class ViewBinding1 : AppCompatActivity() {
    private lateinit var viewBinding: ActivityViewBinding1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.viewBinding = ActivityViewBinding1Binding.inflate(layoutInflater)

        setContentView(viewBinding.root)

        viewBinding.title.text = "我是标题"
        //名称自动转驼峰形式
        viewBinding.clickButton.text = "我是按钮"

        viewBinding.edit.setText("我是输入")

        //可以使用自定义的控件
        viewBinding.edit2.setOnModClickListener {
            val text = viewBinding.edit2.getInputText()
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
        }
        viewBinding.edit2.setInputText("123")
    }
}
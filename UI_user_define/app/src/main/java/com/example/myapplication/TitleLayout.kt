package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.my_title_layout.view.*

//将xml与对应类绑定在一起，能将业务逻辑也封装在一起
class TitleLayout(context:Context,attrs:AttributeSet):LinearLayout(context,attrs) {
    init{
        LayoutInflater.from(context).inflate(R.layout.my_title_layout,this)
    }

    fun setTitle(input:String){
        title.setText(input);
    }
}

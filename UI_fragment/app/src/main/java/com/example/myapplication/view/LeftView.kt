package com.example.myapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import com.example.myapplication.R

class LeftView(context:Context,attrs:AttributeSet):LinearLayout(context,attrs) {
     private lateinit var buttonView:Button

    init{
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_left,this)
        buttonView = view.findViewById(R.id.changeButton)
    }

    fun setButtonText(input:String){
        buttonView.setText(input);
    }
}
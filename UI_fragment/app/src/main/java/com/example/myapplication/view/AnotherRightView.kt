package com.example.myapplication.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import com.example.myapplication.R

class AnotherRightView(context:Context):LinearLayout(context) {

    init{
        LayoutInflater.from(context).inflate(R.layout.fragment_another_right,this)
    }

}
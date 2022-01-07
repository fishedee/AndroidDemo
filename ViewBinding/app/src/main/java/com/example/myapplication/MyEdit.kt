package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.myapplication.databinding.WidgetMyEditBinding

class MyEdit(ctx: Context,attrs:AttributeSet):LinearLayout(ctx,attrs) {
    private var viewBinding: WidgetMyEditBinding

    init{
        val layoutInflator = LayoutInflater.from(ctx)
        viewBinding = WidgetMyEditBinding.inflate(layoutInflator,this,true)
    }

    fun setOnModClickListener(listner:()->Unit){
        viewBinding.clickButton.setOnClickListener { listner() }
    }

    fun setInputText(text:String){
        viewBinding.input.setText(text)
    }

    fun getInputText():String{
        return viewBinding.input.text.toString()
    }
}
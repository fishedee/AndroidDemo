package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton

class MyButton(ctx:Context,attrs: AttributeSet) :AppCompatButton(ctx,attrs){
    private var shouldStopSpread = false

    fun setShouldStopSpread(target:Boolean){
        this.shouldStopSpread = target
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("MyButton","onTouchEvent")
        super.onTouchEvent(event)
        //return false表示会往外传播
        return this.shouldStopSpread
    }
}
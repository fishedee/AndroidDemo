package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class MyTouchView(ctx: Context, attrs:AttributeSet) : View(ctx,attrs){
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when(event.action){
            MotionEvent.ACTION_DOWN->{
                Log.d("MotionEvent","down x = ${x} , y = ${y}")
            }
            MotionEvent.ACTION_MOVE->{
                Log.d("MotionEvent","move x = ${x} , y = ${y}")
            }
            MotionEvent.ACTION_UP->{
                Log.d("MotionEvent","up x = ${x} , y = ${y}")
            }
        }
        return super.onTouchEvent(event)
    }
}
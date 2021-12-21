package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_bubble_event.*

class BubbleEventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubble_event)
        not_bubble_button.setShouldStopSpread(true)
        bubble_button.setShouldStopSpread(false)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d("TouchEvent","Activity touch")
        return super.onTouchEvent(event)
    }
}
package com.example.myapplication

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_frame_animation.*

class FrameAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frame_animation)
        beginFrame.setOnClickListener {
            val animationDrawable = resources.getDrawable(R.drawable.frame_anim) as AnimationDrawable
            frameView.setImageDrawable(animationDrawable)
            animationDrawable.start()
        }
    }
}
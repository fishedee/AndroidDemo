package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import kotlinx.android.synthetic.main.activity_tween_animation.*

class TweenAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tween_animation)
        begin.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this,R.anim.default_tween)
            //动画结束后保持结束状态
            anim.fillAfter = true
            anim.interpolator = LinearInterpolator()
            myImage.startAnimation(anim)
        }
    }
}
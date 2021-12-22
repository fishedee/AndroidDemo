package com.example.myapplication

import android.animation.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationSet
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import kotlinx.android.synthetic.main.activity_property_animation.*

class PropertyAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_animation)
        animator_button1.setOnClickListener {
            val valueAnimator = AnimatorInflater.loadAnimator(this,R.animator.default_normal_animator) as ValueAnimator
            valueAnimator.addUpdateListener { animateValue->
                animator_button1.translationY = animateValue.animatedValue as Float
            }
            //避免内存泄漏
            valueAnimator.setTarget(animator_button1)
            valueAnimator.start()
        }
        animator_button2.setOnClickListener {
            var objectAnimator = AnimatorInflater.loadAnimator(this,R.animator.default_object_animator) as ObjectAnimator
            objectAnimator.target = animator_button2
            objectAnimator.start()
        }
        animator_button3.setOnClickListener {
            var setAnimator = AnimatorInflater.loadAnimator(this,R.animator.default_set_animator) as AnimatorSet
            val firstAnimator = setAnimator.childAnimations[0] as ValueAnimator
            firstAnimator.addUpdateListener { animationValue->
                animator_button3.translationX = animationValue.animatedValue as Float
            }
            firstAnimator.setTarget(animator_button3)
            val secondAnimator = setAnimator.childAnimations[1] as ObjectAnimator
            secondAnimator.target = animator_button3
            setAnimator.start()
        }

    }
}
package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.fragment.AnotherRightFragment
import com.example.myapplication.fragment.LeftFragment
import com.example.myapplication.fragment.RightFragment
import kotlinx.android.synthetic.main.activity_user_view.*
import kotlinx.android.synthetic.main.fragment_left.*

class UserViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_view)

        changeButton.setOnClickListener {
            replaceTop(AnotherRightView(this))
            leftView.setButtonText("按钮改变了")
        }

        replaceTop(RightView(this))
    }

    private fun replaceTop(view:View){
        rightLayout.removeAllViews()
        rightLayout.addView(view)
    }
}
package com.example.myapplication.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_left.*

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        changeButton.setOnClickListener {
            replaceFragment(AnotherRightFragment())
            //获取当前的Fragment
            var leftFragment = supportFragmentManager.findFragmentById(R.id.leftFragment) as LeftFragment
            leftFragment.setButtonText("按钮改了！")
        }

        replaceFragment(RightFragment())
    }
    private fun replaceFragment(fragment:Fragment){
        var fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        //supportFragmentManager.fragments.size表示当前页面有多少个Fragments，而不是栈里面有多少个Fragment
        //前后都是1，commit以后才会改变
        Log.d("fragment before",supportFragmentManager.fragments.size.toString())
        transaction.replace(R.id.rightLayout,fragment)
        Log.d("fragment after",supportFragmentManager.fragments.size.toString())
        if( supportFragmentManager.fragments.size > 1){
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }
}
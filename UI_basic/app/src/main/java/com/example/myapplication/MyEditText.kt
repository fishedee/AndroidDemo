package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText

class MyEditText(ctx:Context,attrs:AttributeSet) :AppCompatEditText(ctx,attrs){

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        this.setOnEditorActionListener { v, actionId, event ->
            if( actionId == EditorInfo.IME_ACTION_SEARCH){
                Toast.makeText(this.context,"键盘的搜索按键",Toast.LENGTH_SHORT).show()
                //需要手动关闭软键盘
                hiddenKeyboard()
                true
            }else{
                false
            }
        }
    }

    private fun showKeyboard(){
        this.requestFocus()
        val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this,InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hiddenKeyboard(){
        val activity = this.context as Activity
        val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocus = activity.currentFocus
        if( imm.isActive && currentFocus != null ){
            val windowToken = currentFocus.windowToken
            if( windowToken != null ){
                imm.hideSoftInputFromWindow(windowToken,InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }
}
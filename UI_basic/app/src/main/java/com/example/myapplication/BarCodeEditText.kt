package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText

class BarCodeEditText(ctx:Context,attrs:AttributeSet) :AppCompatEditText(ctx,attrs){
    private var mBeginning = System.nanoTime()
    private var barCodeListener:((String)->Unit)? = null

    fun setOnBarCodeListener(listener:(String)->Unit){
        this.barCodeListener = listener
    }

    init{
        addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if( (s == null || s.isEmpty()) && start == 0 ){
                    mBeginning = System.nanoTime()
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        setOnEditorActionListener { v, actionId, event ->
            if( actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH){
                val inputText = this.text.toString()
                barCodeListener?.invoke(inputText)
                setText("")
                hiddenKeyboard()
                true
            }else{
                false
            }

        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if( keyCode == KeyEvent.KEYCODE_ENTER){
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if( keyCode == KeyEvent.KEYCODE_ENTER ){
            val inputText = this.text.toString().trim()
            if( inputText.isNotBlank() ){
                barCodeListener?.invoke(inputText)
            }
            this.setText("")
            this.hiddenKeyboard()
            return true
        }
        return super.onKeyUp(keyCode, event)
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
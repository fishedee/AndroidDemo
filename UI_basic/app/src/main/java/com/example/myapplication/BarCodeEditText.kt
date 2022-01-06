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
    private val ONE_MILLION = 1000000
    //设置扫描头的一次输入是300ms
    private val BARCODE_INPUT_INTERVAL = 300
    private var barCodeListener:((String)->Unit)? = null;

    private var onTextChangeListener:((String)->Unit)? = null

    fun setOnBarCodeListener(listener:(String)->Unit){
        this.barCodeListener = listener
    }

    fun setOnTextChangeListener(listener:(String)->Unit){
        this.onTextChangeListener = listener
    }

    init{
        addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                onTextChangeListener?.invoke(s.toString())
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
            val isEnter =
                event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN
            val isInDuration = {
                val duration = (System.nanoTime()-mBeginning)/ONE_MILLION
                duration < BARCODE_INPUT_INTERVAL
            }

            Log.d("barCode","isEnter ${isEnter} isInDuration ${isInDuration()}")

            if( isEnter || actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH){
                val inputText = this.text.toString()
                if( isEnter && isInDuration()){
                    Toast.makeText(this.context,inputText,Toast.LENGTH_SHORT).show()
                    barCodeListener?.invoke(inputText)
                }
                mBeginning = 0
                this.setText("")
                this.hiddenKeyboard()
                false
            }else{
                false
            }

        }
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
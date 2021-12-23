package com.example.myapplication

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener

class BarCodeEditText(ctx:Context,attrs:AttributeSet) :AppCompatEditText(ctx,attrs){
    var mBeginning = System.nanoTime()
    val ONE_MILLION = 1000000
    //设置扫描头的一次输入是300ms
    val BARCODE_INPUT_INTERVAL = 300
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
            val isEnter =
                event != null && event.keyCode === KeyEvent.KEYCODE_ENTER && event.action === KeyEvent.ACTION_DOWN
            val isInDuration = {
                val duration = (System.nanoTime()-mBeginning)/ONE_MILLION
                duration < BARCODE_INPUT_INTERVAL
            }

            if( isEnter || actionId == EditorInfo.IME_ACTION_DONE ){
                val inputText = this.text
                if( isEnter && isInDuration()){
                    Toast.makeText(this.context,inputText,Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this.context,inputText,Toast.LENGTH_SHORT).show()
                }
                mBeginning = 0
                this.setText("")
                false
            }else{
                false
            }

        }
    }
}
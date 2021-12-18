package com.example.myapplication

import android.widget.Toast

fun showToast( text:String){
    Toast.makeText(MyApplication.context,text,Toast.LENGTH_SHORT).show()
}
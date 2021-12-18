package com.example.myapplication

import androidx.lifecycle.ViewModel

class MyViewModel1 :ViewModel(){
    var counter = 0

    override fun onCleared() {
        super.onCleared()

        //ViewModel唯一的生命周期，清除
        counter = 0
    }
}
package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MyViewModel2(initCounter:Int) : ViewModel(){
    private var counter = initCounter

    override fun onCleared() {
        super.onCleared()

        //ViewModel唯一的生命周期，清除
        counter = 0
    }

    fun inc(){
        this.counter++
    }

    fun get():Int{
        return this.counter
    }

    class Factory(private val initCounter:Int):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MyViewModel2(initCounter) as T
        }
    }
}
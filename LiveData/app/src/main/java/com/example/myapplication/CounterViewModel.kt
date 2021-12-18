package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel :ViewModel(){
    val counter:LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    fun plusOne(){
        val data = this._counter.value?:0
        this._counter.value = data+1
    }

    fun initData(){
        this._counter.value = 0
    }
}
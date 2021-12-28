package com.example.myapplication.new_recommend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.receiveAsFlow

class MyViewModel4 :ViewModel(){
    //ui状态
    private val _counter = MutableStateFlow<Int>(0)

    val counter:StateFlow<Int> =_counter

    //副作用
    private val _effect:Channel<String> = Channel()

    val effect = _effect.receiveAsFlow().buffer()

    fun inc(){
        viewModelScope.launch {
            delay(100)
            _counter.value++
        }
    }

    fun minus(){
        viewModelScope.launch {
            delay(100)
            _counter.value--
            _effect.send("减去1了")
        }
    }

    fun setInitValue(data:Int){
        _counter.value = data
    }
}
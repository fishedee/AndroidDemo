package com.example.myapplication.new_recommend2;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

public class MyViewModel5 :ViewModel(){

    //ui状态
    private val _counterState = MutableStateFlow<Int>(0)

    val counterState: StateFlow<Int> =_counterState

    //副作用
    private val _effect: Channel<Int> = Channel()

    val effect = _effect.receiveAsFlow().buffer()

    //本地数据
    private var counter = 0;

    //发送修改数据
    suspend fun inc(){
        this.counter++;
        this._effect.send(this.counter);
        this._counterState.value = this.counter;
    }
}

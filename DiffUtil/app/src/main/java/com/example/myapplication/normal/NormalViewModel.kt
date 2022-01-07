package com.example.myapplication.normal

import androidx.lifecycle.ViewModel
import com.example.myapplication.util.ListEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.receiveAsFlow

class NormalViewModel :ViewModel(){
    private val _listEffect = Channel<ListEvent<List<Todo>>>()

    val listEffect = _listEffect.receiveAsFlow().buffer()

    private var list:ArrayList<Todo> = ArrayList()

    fun init(size:Int):List<Todo>{
        list = ArrayList()
        for( i in (0 until size )){
            this.list.add(Todo(
                id = i +1 ,
                name = "Fish_${i+1}"
            ))
        }
        return this.list
    }

    suspend fun add(){
        var maxId = 0
        this.list.forEach { v->
            if( v.id > maxId ){
                maxId = v.id
            }
        }
        this.list.add(Todo(
            id = maxId +1,
            name = "Fish_${maxId+1}"
        ))
        val lastIndex = this.list.size - 1
        this._listEffect.send(ListEvent(
            data = this.list,
            effect = {view, adapter ->
                view.scrollToPosition(lastIndex)
            }
        ))
    }

    suspend fun del(id:Int){
        this.list = ArrayList(this.list.filter { v->
            v.id != id
        })
        this._listEffect.send(
            ListEvent(
            data = this.list,
            effect = {view, adapter ->  }
        ))
    }

    suspend fun mod(id:Int,name:String){
        this.list.forEach { v->
            if( v.id != id ){
                return@forEach
            }
            v.name = name
        }
        this._listEffect.send(
            ListEvent(
                data = this.list,
                effect = {view, adapter ->  }
            ))
    }
}
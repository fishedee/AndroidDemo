package com.example.myapplication.db2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User2(val name:String,val age:Int) {
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}
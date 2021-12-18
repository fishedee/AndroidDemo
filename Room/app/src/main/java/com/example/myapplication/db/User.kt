package com.example.myapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(val name:String,val age:Int) {
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}
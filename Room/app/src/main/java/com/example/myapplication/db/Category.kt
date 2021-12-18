package com.example.myapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Category(val name:String) {
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}
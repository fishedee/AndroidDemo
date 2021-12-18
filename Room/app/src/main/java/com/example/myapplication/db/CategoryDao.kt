package com.example.myapplication.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {
    @Insert
    fun insert(category: Category): Long


    @Query("select * from category")
    fun getAll(): List<Category>
}
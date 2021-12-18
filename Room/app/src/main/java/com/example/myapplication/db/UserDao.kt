package com.example.myapplication.db

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface UserDao {
    @Insert
    fun insert(user: User): Long

    @Update
    fun update(user: User)

    @Query("delete from User where id = :id")
    fun delete(id: Long)

    @Query("select * from user")
    fun getAll(): List<User>

    @Query("select * from user where name like :nameLike")
    fun getAllByName(nameLike: String): List<User>

    @RawQuery
    fun rawQuery(name:SupportSQLiteQuery): List<User>

    fun getAllByNameRaw(name:String):List<User>{
        val query = "select * from user where name like ?"
        val sqlQuery = SimpleSQLiteQuery(query,arrayOf("%${name}%"))
        return this.rawQuery(sqlQuery)
    }
}
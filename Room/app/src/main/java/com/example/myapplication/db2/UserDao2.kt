package com.example.myapplication.db2


import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.myapplication.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Dao
interface UserDao2 {
    @Insert
    suspend fun insert(user: User2): Long

    @Insert
    fun insertInner(user: User2): Long

    @Update
    suspend fun update(user: User2)

    @Query("delete from User2 where id = :id")
    suspend fun delete(id: Long)

    @Query("delete from User2 where id = :id")
    fun deleteInner(id: Long)

    @Query("select * from user2")
    suspend fun getAll(): List<User2>

    @RawQuery
    suspend fun rawQuery(name:SupportSQLiteQuery): List<User2>

    suspend fun getAllByNameRaw(name:String):List<User2>{
        val query = "select * from user2 where name like ?"
        val sqlQuery = SimpleSQLiteQuery(query,arrayOf("%${name}%"))
        return this.rawQuery(sqlQuery)
    }

    suspend fun deleteAndInsert(id:Long,user:User2){
        withContext(Dispatchers.IO){
            AppDatabase2.getDatabase().runInTransaction {
                deleteInner(id)
                insertInner(user)
            }
        }
    }
}
package com.example.myapplication.db2

import com.example.myapplication.db.Category
import com.example.myapplication.db.CategoryDao
import com.example.myapplication.db2.User2
import com.example.myapplication.db.UserDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.MyApplication

@Database(version = 2, entities = [User2::class])
abstract  class AppDatabase2:RoomDatabase() {
    abstract fun userDao2(): UserDao2

    companion object{
        private var instance: AppDatabase2? = null

        @Synchronized
        fun getDatabase(): AppDatabase2 {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(MyApplication.context, AppDatabase2::class.java,
                "app_database2")
                //默认不允许在主线程操作
                //.allowMainThreadQueries()
                //schema不对齐的时候，直接删除
                //.fallbackToDestructiveMigration()
                .build().apply {
                    instance = this
                }
        }
    }

}
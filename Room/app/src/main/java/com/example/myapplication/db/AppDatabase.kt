package com.example.myapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(version = 2, entities = [User::class,Category::class])
abstract  class AppDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun categoryDao():CategoryDao

    companion object{
        private val MIGRATION_1_2 = object :Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("create table Category("+
                        "id integer primary key autoincrement not null,"+
                        "name text not null"+
                        ")");
            }
        }
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,
            "app_database")
                    //默认不允许在主线程操作
                //.allowMainThreadQueries()
                    //schema不对齐的时候，直接删除
                //.fallbackToDestructiveMigration()
                .addMigrations(MIGRATION_1_2)
                .build().apply {
                instance = this
            }
        }
    }

}
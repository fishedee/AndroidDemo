package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MySqlDatabaseHelper(val context:Context,name:String,version:Int) :SQLiteOpenHelper(context,name,null,version){

    private val createBook ="create table Book( "+
            " id integer primary key autoincrement,"+
            " author text,"+
            "name text," +
            "category_id integer )"

    private val createCategory ="create table Category( "+
            " id integer primary key autoincrement,"+
            " category_name text,"+
            " category_code integer) "

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createBook)
        db.execSQL(createCategory)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if( oldVersion <= 1 ){
            db.execSQL(createBook)
        }
        if( oldVersion <= 2 ){
            db.execSQL(createCategory)
        }
    }
}
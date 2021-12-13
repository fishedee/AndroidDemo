package com.example.myapplication

import android.annotation.SuppressLint
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_sqlite.*
import java.lang.Exception

class SQLiteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        val dbHeper = MySqlDatabaseHelper(this,"BookStore.db",2)

        add.setOnClickListener {
            val db = dbHeper.writableDatabase
            //开始事务
            db.beginTransaction()
            try{
                db.execSQL("insert into Book(author,name,category_id)values(\"fish\",\"book1\",1001)")
                //提交事务
                db.setTransactionSuccessful()
            }catch(e:Exception){
                e.printStackTrace()
            }finally {
                //结束事务
                db.endTransaction()
            }
        }

        del.setOnClickListener {
            val db = dbHeper.writableDatabase
            db.execSQL("delete from Book where author = \"fish\"")
        }

        mod.setOnClickListener {
            val db = dbHeper.writableDatabase
            db.execSQL("update Book set author = \"fish2\" where author = \"fish\"")
        }

        query.setOnClickListener {
            val db = dbHeper.readableDatabase
            val cursor = db.rawQuery("select id,author,name,category_id from Book where category_id = ?",arrayOf("1001"))
            if( cursor.moveToFirst()){
                do{
                    @SuppressLint("RANGE")
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    @SuppressLint("RANGE")
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    @SuppressLint("RANGE")
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    @SuppressLint("RANGE")
                    val category_id = cursor.getInt(cursor.getColumnIndex("category_id"))
                    Log.d("Book","id ${id},author ${author},name ${name},category_id ${category_id}")
                }while(cursor.moveToNext())
            }
            cursor.close()
        }
    }
}
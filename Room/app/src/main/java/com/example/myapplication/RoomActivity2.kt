package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.Category
import com.example.myapplication.db.User
import com.example.myapplication.db2.AppDatabase2
import com.example.myapplication.db2.User2
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.coroutines.*

class RoomActivity2 : AppCompatActivity() {
    lateinit var database: AppDatabase2

    val gson: Gson

    init{
        val gsonBuilder = GsonBuilder()
        gsonBuilder.serializeNulls()
        gsonBuilder.disableHtmlEscaping()
        gson = gsonBuilder.create()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun showDialog(text:String){
        AlertDialog.Builder(this).apply {
            setTitle("提示")
            setMessage(text)
            //不能点击灰白处关闭
            setCancelable(false)
            //闭包的两个参数
            setPositiveButton("确认"){dialog,which->}
            setNegativeButton("取消"){dialog,which->}
        }.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room2)
        this.database = AppDatabase2.getDatabase()

        insert.setOnClickListener {
            lifecycleScope.launch {
                database.userDao2().insert(User2("fish",123))
            }
        }

        delete.setOnClickListener {
            lifecycleScope.launch {
                val delIdText = delId.text.toString()
                if( delIdText.isBlank() ){
                    showDialog("请输入ID")
                    return@launch
                }
                database.userDao2().delete(delIdText.toLong())
            }
        }

        update.setOnClickListener {
            lifecycleScope.launch {
                val modIdText = modId.text.toString()
                if( modIdText.isBlank() ){
                    showDialog("请输入ID")
                    return@launch
                }
                val user = User2("fish2",789)
                user.id = modIdText.toLong()
                database.userDao2().update(user)
            }
        }

        getAll.setOnClickListener {
            lifecycleScope.launch {
                val userList = database.userDao2().getAll()
                showAll.text = gson.toJson(userList)
            }
        }

        getRaw.setOnClickListener {
            lifecycleScope.launch {
                val userList = database.userDao2().getAllByNameRaw("2")
                showAll.text = gson.toJson(userList)
            }
        }
    }
}
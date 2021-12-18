package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.Category
import com.example.myapplication.db.User
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.coroutines.*

class RoomActivity : AppCompatActivity() {
    val mainScope = MainScope()
    lateinit var database:AppDatabase

    val gson: Gson

    init{
        val gsonBuilder = GsonBuilder()
        gsonBuilder.serializeNulls()
        gsonBuilder.disableHtmlEscaping()
        gson = gsonBuilder.create()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
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
        setContentView(R.layout.activity_room)

        database = AppDatabase.getDatabase(this.applicationContext)

        insert.setOnClickListener {
            mainScope.launch {
                withContext(Dispatchers.IO){
                    database.userDao().insert(User("fish",123))
                }
            }
        }

        delete.setOnClickListener {
            mainScope.launch {
                val delIdText = delId.text.toString()
                if( delIdText.isBlank() ){
                    showDialog("请输入ID")
                    return@launch
                }
                withContext(Dispatchers.IO) {
                    //走事务模式
                    database.runInTransaction {
                        database.userDao().delete(delIdText.toLong())
                    }
                }
            }
        }

        update.setOnClickListener {
            mainScope.launch {
                val modIdText = modId.text.toString()
                if( modIdText.isBlank() ){
                    showDialog("请输入ID")
                    return@launch
                }
                val user = User("fish2",789)
                user.id = modIdText.toLong()
                withContext(Dispatchers.IO) {
                    database.userDao().update(user)
                }
            }
        }

        getAll.setOnClickListener {
            mainScope.launch {
                val userList = withContext(Dispatchers.IO) {
                    database.userDao().getAll()
                }
                showAll.text = gson.toJson(userList)
            }
        }

        getRaw.setOnClickListener {
            mainScope.launch {
                val userList = withContext(Dispatchers.IO) {
                    database.userDao().getAllByNameRaw("2")
                }
                showAll.text = gson.toJson(userList)
            }
        }

        addCategory.setOnClickListener {
            mainScope.launch {
                withContext(Dispatchers.IO){
                    database.categoryDao().insert(Category("mk"))
                }
            }
        }

        getAllCategory.setOnClickListener {
            mainScope.launch {
                val categoryList = withContext(Dispatchers.IO){
                    database.categoryDao().getAll();
                }
                showAll.text = gson.toJson(categoryList)
            }
        }
    }
}
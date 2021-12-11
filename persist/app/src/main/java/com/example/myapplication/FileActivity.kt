package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_file.*
import java.io.*
import java.lang.StringBuilder

class FileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        val inputText = load()
        if( inputText.isNotEmpty() ){
            editText.setText(inputText)
            editText.setSelection(inputText.length)
            Toast.makeText(this,"Restoring succeded",Toast.LENGTH_SHORT).show()
        }
    }

    override  fun onDestroy(){
        super.onDestroy()
        val inputText = editText.text.toString()
        save(inputText)
    }

    private fun save(content:String){
        try{
            //MODE_PRIVATE是清空重新写入，MODE_APPEND是尾部添加
                //这种方式读写文件，不允许有路径
            val output = openFileOutput("data.txt", MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use{
                it.write(content)
            }
        }catch(e:IOException){
            e.printStackTrace()
        }
    }

    private fun load():String{
        var content = StringBuilder()
        try{
            //这种方式读写文件，不允许有路径
            val input = openFileInput("data.txt")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it+"\n")
                }
            }
        }catch(e:IOException){
            e.printStackTrace()
        }
        return content.toString()
    }
}
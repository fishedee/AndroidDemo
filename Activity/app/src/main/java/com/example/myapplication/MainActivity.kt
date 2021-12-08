package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button = findViewById<Button>(R.id.button1);
        button.setOnClickListener {
            Toast.makeText(this,"You clicked button1",Toast.LENGTH_SHORT).show();
        }
        //kotlin-android-extensions，使用这个插件自动生成出来的，不需要再用findViewById
        button2.setOnClickListener {
            Toast.makeText(this,"You clicked button2",Toast.LENGTH_SHORT).show();
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //将menu xml实例化到menu实体上
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add_item->Toast.makeText(this,"You clicked add_item",Toast.LENGTH_SHORT).show()
            R.id.remove_item->Toast.makeText(this,"You clicked remove_item",Toast.LENGTH_SHORT).show()
        }
        return true
    }
}
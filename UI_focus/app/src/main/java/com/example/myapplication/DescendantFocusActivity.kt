package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.example.myapplication.adapter.Todo
import com.example.myapplication.adapter.TodoAdapter
import kotlinx.android.synthetic.main.activity_descendant_focus.*

class DescendantFocusActivity : AppCompatActivity() {
    private lateinit var todoList1 :ArrayList<Todo>
    private lateinit var todoList2 :ArrayList<Todo>;
    private lateinit var todoList3 :ArrayList<Todo>;


    private fun initTodoList():ArrayList<Todo>{
        val todoList = ArrayList<Todo>()
        for( i in 0..100){
            todoList.add(Todo("job_"+i,"fish_"+i))
        }
        return todoList
    }

    private fun initList(listView:ListView,adapter:TodoAdapter){
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            Log.d("listView","click "+position)
            var todo = adapter.getData()[position]
            Toast.makeText(this,todo.title,Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descendant_focus)

        todoList1 = initTodoList()
        todoList2 = initTodoList()
        todoList3 = initTodoList()
        val todoAdapterList = listOf(
            TodoAdapter(this,R.layout.widget_list_view,todoList1),
            TodoAdapter(this,R.layout.widget_list_view2,todoList2),
            TodoAdapter(this,R.layout.widget_list_view3,todoList3)
        )
        val listViewList = listOf(
            listView1,
            listView2,
            listView3
        )
        for( i in listViewList.indices ){
            initList(listViewList[i],todoAdapterList[i])
        }
    }
}
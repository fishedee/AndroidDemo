package com.example.myapplication;

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView

//需要传递父级，Item的资源ID，和数据
class TodoAdapter(activity: Activity,val resourceId:Int,data:List<Todo>):ArrayAdapter<Todo>(activity,resourceId,data){

    inner class ViewHolder(val textView: TextView, val button:Button){}

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view:View
        if( convertView == null ){
            //从context中生成Layout，Layout的资源ID为resourceId，Parent为parent，不绑定到root中
            view = LayoutInflater.from(context).inflate(resourceId,parent,false)

            //获取View，并缓存各自的数据对象
            val textView = view.findViewById<TextView>(R.id.text)
            val button = view.findViewById<Button>(R.id.del)
            view.tag = ViewHolder(textView, button)
        }else{
            //沿用现有的convertView
            view = convertView;
        }

        //填充数据
        var viewHolder = view.tag as ViewHolder
        val todo = getItem(position)
        if( todo != null ){
            viewHolder.button.setText("["+todo.user+"]添加")
            viewHolder.textView.setText(todo.title)
        }
        return view
    }
}

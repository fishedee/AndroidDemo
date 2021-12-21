package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import kotlinx.android.synthetic.main.activity_handler_event.*
import java.lang.ref.WeakReference
import java.util.*

class HandlerEventActivity : AppCompatActivity() {
    var timer:Timer? = null

    var currentCounter:Int = 10

    class MyHandler(val contenxt:HandlerEventActivity):Handler(){
        //使用WeakReference避免内存泄漏
        private val mActivty: WeakReference<HandlerEventActivity> = WeakReference(contenxt);

        override fun handleMessage(msg: Message) {
            if( msg.what == 110 ){
                val text = msg.data.getString("value") ?:""
                mActivty.get()?.setCounterText(text)
            }
        }
    }
    val handler = MyHandler(this)

    fun setCounterText(data:String){
        showCount.text = data
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_event)
        beginCount.setOnClickListener {
            if( timer != null ){
                timer?.cancel()
                timer = null
            }
            //Timer在额外的线程上
            timer = Timer()
            currentCounter = 11
            timer?.schedule(object :TimerTask(){
                override fun run() {
                    if( currentCounter <= 0 ){
                        timer?.cancel()
                        timer = null
                        return
                    }
                    currentCounter--

                    val message = Message()
                    message.what = 110
                    val bundle = Bundle()
                    bundle.putString("value",currentCounter.toString())
                    message.data = bundle
                    handler.sendMessage(message)
                }
            },0,1000)
        }
    }
}
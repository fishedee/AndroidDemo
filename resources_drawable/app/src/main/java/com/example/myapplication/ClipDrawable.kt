package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import kotlinx.android.synthetic.main.activity_clip_drawable.*
import java.lang.ref.WeakReference
import java.util.*

class ClipDrawable : AppCompatActivity() {
    class MyHandler(val context:ClipDrawable):Handler(){
        private val ctx:WeakReference<ClipDrawable> = WeakReference(context);

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            ctx.get()?.setLevel(msg.arg1)
        }
    }

    val myHandler = MyHandler(this)

    private fun setLevel(level:Int){
        val clipDrawable = imageClip.drawable as android.graphics.drawable.ClipDrawable
        clipDrawable.level = level
    }

    private var level  = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clip_drawable)

        val timer = Timer()
        timer.schedule(object:TimerTask(){
            override fun run() {
                if( level >= 10000 ){
                    timer.cancel()
                    return;
                }
                val newLevel = level+100
                level = newLevel
                val msg = Message()
                msg.what = 123
                msg.arg1 = newLevel
                myHandler.sendMessage(msg)
            }
        },0,300)
    }
}
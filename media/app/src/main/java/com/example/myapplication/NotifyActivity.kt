package com.example.myapplication

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_notify.*

class NotifyActivity : AppCompatActivity(),View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify)
        this.createChannel()
        normalNotify.setOnClickListener(this)
        largeTextNotify.setOnClickListener(this)
        largeImageNotify.setOnClickListener(this)
        startService.setOnClickListener {
            val intent = Intent(this,MyService::class.java)
            startService(intent)
        }
        stopService.setOnClickListener {
            var intent = Intent(this,MyService::class.java)
            stopService(intent)
        }
    }

    private lateinit var manager:NotificationManager;

    private fun createChannel(){
        this.manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            val channel = NotificationChannel("normal","Normal",NotificationManager.IMPORTANCE_DEFAULT)
            this.manager.createNotificationChannel(channel)
        }
    }

    override fun onClick(v: View?) {
        if( v == normalNotify){
            var intent = Intent(this,MainActivity::class.java)
            var pi = PendingIntent.getActivity(this,0,intent,0)
            //注意传入渠道名
            val notification = NotificationCompat.Builder(this,"normal")
                .setContentTitle("This is Content Title")
                .setContentText("This is Content text")
                .setSmallIcon(R.drawable.small)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.large))
                .setContentIntent(pi)//点击后的打开
                .setAutoCancel(true)//点击后自动删除通知
                .build()
            //notify的id用来区分重复的通知
            this.manager.notify(1,notification)
        }else if( v == largeTextNotify){
            var intent = Intent(this,MainActivity::class.java)
            var pi = PendingIntent.getActivity(this,0,intent,0)
            //注意传入渠道名
            val notification = NotificationCompat.Builder(this,"normal")
                .setContentTitle("This is Content Title")
                .setStyle(NotificationCompat.BigTextStyle().bigText("Learn how to to build notifications, send and sync data, and use voice actions.Get the official Android IDE and developer tools to build apps for Android"))
                .setSmallIcon(R.drawable.small)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.large))
                .setContentIntent(pi)//点击后的打开
                .setAutoCancel(true)//点击后自动删除通知
                .build()
            //notify的id用来区分重复的通知
            this.manager.notify(2,notification)
        }else if( v == largeImageNotify){
            var intent = Intent(this,MainActivity::class.java)
            var pi = PendingIntent.getActivity(this,0,intent,0)
            //注意传入渠道名
            val notification = NotificationCompat.Builder(this,"normal")
                .setContentTitle("This is Content Title")
                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources,R.drawable.large)))
                .setSmallIcon(R.drawable.small)
                .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.large))
                .setContentIntent(pi)//点击后的打开
                .setAutoCancel(true)//点击后自动删除通知
                .build()
            //notify的id用来区分重复的通知
            this.manager.notify(3,notification)
        }
    }
}
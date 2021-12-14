package com.example.myapplication

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MyService : Service() {

    private fun createChannel():NotificationManager{
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            val channel = NotificationChannel("my_service","前台Service通知", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        return manager
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MyService","onCreate")
        var intent = Intent(this,MainActivity::class.java)
        var pi = PendingIntent.getActivity(this,0,intent,0)
        //注意传入渠道名
        val notification = NotificationCompat.Builder(this,"my_service")
            .setContentTitle("This is Content Title")
            .setContentText("This is Content text")
            .setSmallIcon(R.drawable.small)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.large))
            .setContentIntent(pi)//点击后的打开
            .build()
        //启动前台通知
        //前台通知的特点是，与服务绑定在一起，通知在的时候，服务就在，通知不在的时候，服务就不在。
        //前台服务无法简单地划走，用户需要明确指定关闭该服务的时候，才能关闭它
        startForeground(1,notification)
    }
}
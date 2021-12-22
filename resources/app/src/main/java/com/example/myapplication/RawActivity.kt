package com.example.myapplication

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_raw.*
import java.io.FileDescriptor

class RawActivity : AppCompatActivity() {
    private lateinit var mediaPlayer1:MediaPlayer
    private lateinit var mediaPlayer2:MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_raw)

        //读取raw资源
        mediaPlayer1 = MediaPlayer.create(this,R.raw.a)

        //raw资源不能用prepare
        //mediaPlayer1.prepareAsync()

        //读取assets资源，获取FileDescriptor的方式
        val afd = resources.assets.openFd("b.mp3")
        mediaPlayer2 = MediaPlayer()
        mediaPlayer2.setDataSource(afd.fileDescriptor,afd.startOffset,afd.length)
        mediaPlayer2.prepareAsync()
        play1.setOnClickListener {
            mediaPlayer1.start()
        }
        pause1.setOnClickListener {
            if( mediaPlayer1.isPlaying ){
                mediaPlayer1.pause()
            }
        }
        play2.setOnClickListener {
            mediaPlayer2.start()
        }
        pause2.setOnClickListener {
            if( mediaPlayer2.isPlaying ){
                mediaPlayer2.pause()
            }
        }
    }
}
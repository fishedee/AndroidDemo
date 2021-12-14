package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_album.*
import kotlinx.android.synthetic.main.activity_album.imageView
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.FileDescriptor

class AlbumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        selectAlbum.setOnClickListener {
            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type="image/*"
            startActivityForResult(intent,100)
        }
        selectAlbum2.setOnClickListener {
            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type="image/*"
            startActivityForResult(intent,101)
        }
    }

    private fun getUriBitmap(uri: Uri):Bitmap?{
        val fd = contentResolver.openFileDescriptor(uri,"r")
        //使用openFileDescriptor的话要记得放在use里面关闭
        fd.use {
            if( fd != null ){
                return android.graphics.BitmapFactory.decodeFileDescriptor(fd.fileDescriptor)
            }else{
                return null
            }
        }
    }

    private fun getUriBitmap2(uri:Uri):Bitmap{
        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
        return bitmap
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if( requestCode == 100 ){
            if( resultCode == RESULT_OK && data != null ){
                data.data?.let { uri->
                    val bitmap = this.getUriBitmap(uri)
                    imageView.setImageBitmap(bitmap)
                }
            }
        }else if( requestCode == 101 ){
            if( resultCode == RESULT_OK && data != null ){
                data.data?.let { uri->
                    val bitmap = this.getUriBitmap2(uri)
                    imageView.setImageBitmap(bitmap)
                }
            }
        }
    }
}
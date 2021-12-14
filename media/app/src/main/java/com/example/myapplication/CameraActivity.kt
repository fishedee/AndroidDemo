package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File

class CameraActivity : AppCompatActivity() {

    private lateinit var imageUri:Uri;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        takePhoto.setOnClickListener {
            getNewFileUri()
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
            startActivityForResult(intent,100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when( requestCode){
            100->{
                if( resultCode == RESULT_OK){
                    val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))
                    imageView.setImageBitmap(bitmap)
                }
            }
        }
    }

    private fun getNewFileUri(){
        val outputImage = File(externalCacheDir,"output_image.jpg")
        if( outputImage.exists() ){
            outputImage.delete()
        }
        outputImage.createNewFile()
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ){
            imageUri = FileProvider.getUriForFile(this,"com.fishedee.fileProvider",outputImage)
        }else{
            imageUri = Uri.fromFile(outputImage)
        }
    }
}
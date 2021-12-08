package com.example.myapplication.explicit_result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.explicit.ExplicitSecondActivity
import kotlinx.android.synthetic.main.activity_main.*

class ExplicitResultFirstActivity : AppCompatActivity() {
    val requestCode = 101;
    val logName = ExplicitResultFirstActivity::class.simpleName;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit_result_first)
        button1.setOnClickListener {
            //通过直接指定Activity类名的方式来启动Intent
            var intent = Intent(this, ExplicitResultSecondActivity::class.java);
            intent.putExtra("name","fish");
            startActivityForResult(intent,requestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if( requestCode == this.requestCode ){
            Log.d(logName,"requestCode is ${requestCode} resultCode is ${resultCode}");
            if( resultCode == RESULT_OK){
                var returnedData = data?.getStringExtra("age");
                Log.d(logName,"result is ${returnedData}");
            }
        }
    }
}
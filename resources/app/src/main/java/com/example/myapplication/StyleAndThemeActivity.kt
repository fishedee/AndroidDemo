package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class StyleAndThemeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_style_and_theme)
        setTheme(R.style.CrazyTheme)
    }
}
package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.myapplication.R


class LeftFragment : Fragment() {
    lateinit var changeButton:Button;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_left, container, false)
        this.changeButton = view.findViewById<Button>(R.id.changeButton)
        return view
    }

    fun setButtonText(text:String){
        changeButton.setText(text)
    }

}
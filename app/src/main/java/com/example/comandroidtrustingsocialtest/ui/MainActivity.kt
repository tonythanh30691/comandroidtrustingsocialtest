package com.example.comandroidtrustingsocialtest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.comandroidtrustingsocialtest.R
import com.example.comandroidtrustingsocialtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    fun validateData(view: View) {
        if (binding.phoneEditText.isPhoneValid()) {
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "NG", Toast.LENGTH_SHORT).show()
        }
    }
}

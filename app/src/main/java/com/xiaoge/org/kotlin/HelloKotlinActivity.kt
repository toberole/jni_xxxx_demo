package com.xiaoge.org.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xiaoge.org.R

class HelloKotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hello_kotlin)
        val arr1 = arrayOf(1, 2)
    }
}
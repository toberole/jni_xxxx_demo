package com.xiaoge.org.activity.kotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.xiaoge.org.R
import kotlinx.android.synthetic.main.hello_kotlin.*

/**
 * 引用this 类似java的Activity.this
 * this@HelloKotlinActivity
 */
class HelloKotlinActivity : AppCompatActivity(), View.OnClickListener {
    // 需要引用kotlin反射包
    private var TAG = HelloKotlinActivity::class.simpleName

    private lateinit var et_name: EditText
    private lateinit var btn_login: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hello_kotlin)

        initView()
    }

    private fun initView() {
        et_name = findViewById(R.id.et_name)

        btn_login = findViewById(R.id.btn_login)
        btn_login.setOnClickListener(this)

        // 不用findViewById tv_title
        btn_reg.setOnClickListener(this)

        // 不用findViewById tv_title
        tv_title.text = "Hello Kotlin"
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when (id) {
            R.id.btn_login -> {
                var name: String
                name = et_name.text.toString().trim()
                Log.i(TAG, "login name: " + name)

                if (name.isNotEmpty()) {
                    Toast.makeText(this@HelloKotlinActivity, name, Toast.LENGTH_SHORT).show()
                }
            }

            R.id.btn_reg -> {
                Log.i(TAG, "reg")

                // ::双冒号是 JDK1.8 的新特性，表示方法引用
                var intent = Intent(this@HelloKotlinActivity, DemoKotlinActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
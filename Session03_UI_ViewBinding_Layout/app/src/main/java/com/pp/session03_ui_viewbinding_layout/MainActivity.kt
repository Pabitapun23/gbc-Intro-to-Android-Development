package com.pp.session03_ui_viewbinding_layout

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.pp.session03_ui_viewbinding_layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // ActivityMainBinding - automatically generated class based on our activity_main.xml in layout
    private lateinit var binding: ActivityMainBinding
    private var counter = 0

//    lateinit var tvTitle : TextView
//    lateinit var btnClickMe :  Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        // inflate() - this is used to generate UI using classes like - layoutInflater
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // it's correct only if you want to use it here, in this function block.
        // var tvTitle = findViewById<TextView>(R.id.tv_title)

//        tvTitle = findViewById(R.id.tv_title)
//        btnClickMe = findViewById(R.id.btn_click_me)

//        btnClickMe.setOnClickListener {
//            this.displayWelcomeMessage()
//        }

        this.binding.btnClickMe.setOnClickListener {
            this.displayWelcomeMessage()
        }
    }

    fun displayWelcomeMessage() {
//        tvTitle.setText("Welcome to Wednesday!")

        // using binding class
        this.binding.tvTitle.setText("Welcome to Wednesday!")

        this.binding.tvCounter.setText("${++counter}")
    }
}
package com.pp.session2_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: onCreate() Called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: called")
    }
}
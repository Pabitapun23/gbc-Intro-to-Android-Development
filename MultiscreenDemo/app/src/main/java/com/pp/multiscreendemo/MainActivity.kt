package com.pp.multiscreendemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pp.multiscreendemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // For red screen
        this.binding.btnScreenRed.setOnClickListener {
            this.goToRed()
        }

        // For blue screen
        this.binding.btnScreenBlue.setOnClickListener {
            this.goToBlue()
        }
    } // onCreate()

    private fun goToRed() {
        // create an object of Product class using information input by user
        val product = Product("BMW M3", 2, 30000.0)

        // navigate to red screen
        val redIntent = Intent(this@MainActivity, RedActivity::class.java)
        redIntent.putExtra("extra_product_obj", product)     //product -> Serializable interface or protocol
        startActivity(redIntent)
    }

    private fun goToBlue() {
        val blueIntent = Intent(this@MainActivity, BlueActivity::class.java)

        // to send the data to next screen
        // can send any type of data to next screen; it can be String, Int, Double.
        blueIntent.putExtra("extra_product_name", "Airpods")
        blueIntent.putExtra("extra_product_quantity", 2)
        blueIntent.putExtra("extra_product_price", 600.0)

        startActivity(blueIntent)
    }
}
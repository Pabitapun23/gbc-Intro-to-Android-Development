package com.pp.multiscreendemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pp.multiscreendemo.databinding.ActivityBlueBinding

class BlueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBlueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_blue)

        this.binding = ActivityBlueBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // access the Intent of current activity
        val currentIntent = this@BlueActivity.intent

        if (currentIntent != null) {
            // access the extra data sent by previous screen through intent
            // it's case-sensitive so name must be correct
            var name = currentIntent.getStringExtra("extra_product_name").toString()
            var quantity = currentIntent.getIntExtra("extra_product_quantity", 0)
            var price = currentIntent.getDoubleExtra("extra_product_price", 0.0)

            this.binding.tvNameData.text = name
            this.binding.tvQuantityData.text = quantity.toString()
            this.binding.tvPriceData.text = price.toString()

        }
    }
}
package com.pp.multiscreendemo

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.pp.multiscreendemo.databinding.ActivityRedBinding

class RedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRedBinding
    private lateinit var productObj : Product
    private val TAG : String = "RedActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_red)

        this.binding = ActivityRedBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // display menu bar on the screen
        setSupportActionBar(this.binding.menuToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        val currentIntent = this@RedActivity.intent

        if(currentIntent != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                productObj = currentIntent.getSerializableExtra("extra_product_obj", Product::class.java)!!
            } else {
                // do casting to convert it to product type -> like as Product
                productObj = currentIntent.getSerializableExtra("extra_product_obj") as Product
            }
        } else {
            productObj = Product("NA", 0, 0.0)
        }

        Log.d(TAG, "onCreate: productObj : $productObj")
        // update the UI with product information

        this.binding.tvNameData.text = productObj.productName
        this.binding.tvQuantityData.text = productObj.quantity.toString()
        this.binding.tvPriceData.text = productObj.price.toString()
        this.binding.tvTotalData.text = productObj.total.toString()
        this.binding.tvTaxData.text = productObj.tax.toString()
        this.binding.tvFinalPriceData.text = productObj.finalPrice.toString()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // inflate() - it inflates the xml file to generate menus
        menuInflater.inflate(R.menu.menu_options, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            R.id.menu_item_green -> {
                Log.d(TAG, "onOptionsItemSelected: Green is selected")
                return true
            }
            R.id.menu_item_yellow-> {
                Log.d(TAG, "onOptionsItemSelected: Yellow is selected")

                // navigate to yellow screen
                val yellowIntent = Intent(this@RedActivity, YellowActivity::class.java)
                startActivity(yellowIntent)

                return true
            }
            R.id.menu_item_cyan -> {
                Log.d(TAG, "onOptionsItemSelected: Cyan is selected")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
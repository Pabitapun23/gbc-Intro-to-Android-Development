package com.pp.parking_pabita

import android.app.Activity
import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.BoolRes
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.snackbar.Snackbar
import com.pp.parking_pabita.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG : String = "MainActivity"

    // List of Strings
    private var receiptsList: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // display menu bar on the screen
        setSupportActionBar(this.binding.menuToolbar)
        // Change the title
        supportActionBar?.title = "Parking Lot Payment App"

        // For changing the color of overflow icon
        // Get the drawable for the overflow icon
        val drawable = this.binding.menuToolbar.overflowIcon

        // Apply a tint to change the color of the overflow icon
        if (drawable != null) {
            val wrappedDrawable = DrawableCompat.wrap(drawable).mutate()
            DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(this, R.color.white))
            binding.menuToolbar.overflowIcon = wrappedDrawable
        }

        // Calculate the Parking Fee
        this.binding.btnPay.setOnClickListener {

            // For radiobutton selection
            val selectedParkingLot = when (binding.rgParkingLot.checkedRadioButtonId) {
                binding.rbParkingA.id -> binding.rbParkingA.text.toString()
                binding.rbParkingB.id -> binding.rbParkingB.text.toString()
                else -> {
                    // it handles the case when no RadioButton is selected
                    binding.errorMessage.setText("ERROR: Select a parking lot")
                }
            }

            binding.rbParkingA.setOnClickListener {
                // Reset the error message to empty when rbParkingA is clicked
                binding.errorMessage.text = ""
            }

            binding.rbParkingB.setOnClickListener {
                // Reset the error message to empty when rbParkingB is clicked
                binding.errorMessage.text = ""
            }

            // get the number of hours
            val etParkingHours : String = binding.etParkingHours.text.toString()

            // error handling
            if (etParkingHours.isEmpty()) {
                this.binding.etParkingHours.setError("ERROR: All fields must be filled in!")
                return@setOnClickListener
            }

            // convert this String hours to Double
            val hoursFromUI : Double = etParkingHours.toDouble()
            val hoursFromUIWithRoundOff = String.format("%.2f", hoursFromUI).toDouble()


            // get the license number
            val licensePlateFromUI : String = binding.etLicensePlate.text.toString()
            // error handling
            if (licensePlateFromUI.isEmpty()) {
                this.binding.etLicensePlate.setError("ERROR: All fields must be filled in!")
                return@setOnClickListener
            }

            // For total amount to be paid
            // Parking lot A costs $2.50 per hour
            // Parking lot B costs $3.50 per hour
            var amountBeforeRoundOff = 0.0
            var amountToBePaid : Double = 0.0
            if (binding.rbParkingA.isChecked) {
                amountBeforeRoundOff = hoursFromUI * 2.50
                // after rounding off to 2 decimal points
                amountToBePaid = String.format("%.2f", amountBeforeRoundOff).toDouble()

            } else if(binding.rbParkingB.isChecked) {
                amountBeforeRoundOff = hoursFromUI * 3.50
                amountToBePaid = String.format("%.2f", amountBeforeRoundOff).toDouble()
            }

            // Outputs the receipt to the screen
            val output : String = "RECEIPT \n" +
                    "$selectedParkingLot \n" +
                    "License Plate: $licensePlateFromUI \n" +
                    "Hours: $hoursFromUIWithRoundOff \n" +
                    "Total Amount Paid: $$amountToBePaid"

            binding.tvReceipt.setText(output)

            receiptsList.add(output)

            // Clear all form fields and prepare for new input
            binding.rgParkingLot.clearCheck()
            binding.etLicensePlate.setText("")
            binding.etParkingHours.setText("")
        }

    }

    override fun onRestart() {
        super.onRestart()
        binding.tvReceipt.setText("")
        binding.errorMessage.setText("")
        Log.d(TAG, "onRestart: called")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            R.id.menu_item -> {
                Log.d(TAG, "onOptionsItemSelected: History option is selected")

                Log.d(TAG, "Receipts List: $receiptsList")
                // navigate to 2nd screen
                val intent = Intent(this@MainActivity, HistoryActivity::class.java)

                // to send the data to next screen
                intent.putExtra("PARKING_RECEIPT_EXTRA", ArrayList(receiptsList))

                startActivity(intent)

                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
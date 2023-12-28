package com.pp.hydro_pabita

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.pp.hydro_pabita.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // Change button color programmatically
        this.binding.btnCalculate.setBackgroundColor(0xff2196F3.toInt())

        // Change background color of error message programmatically
        //this.binding.errorMessage.setBackgroundColor(0xffE6746B.toInt())


        this.binding.btnCalculate.setOnClickListener {
            // gets morning usage from textbox
            val etMorningUsage : String = this.binding.etMorningUsage.text.toString()

            this.binding.errorMessage.setText("")
            this.binding.errorMessage.setBackgroundColor(Color.WHITE)

            // error handling
            //   - display error message if evening usage field is empty
            if (etMorningUsage.isEmpty()) {
                this.binding.errorMessage.setText("ERROR: All field must be filled in!")
                this.binding.errorMessage.setBackgroundColor(0xffF6CFCF.toInt())
                return@setOnClickListener
            }

            val morningUsageFromUI : Double = etMorningUsage.toDouble()

            // gets evening usage from textbox
            val etEveningUsage : String = this.binding.etEveningUsage.text.toString()

            // error handling
            //  - display error message if evening usage field is empty
            if (etEveningUsage.isEmpty()) {
                this.binding.errorMessage.setText("ERROR: All fields must be filled in!")
                this.binding.errorMessage.setBackgroundColor(0xffF6CFCF.toInt())
                return@setOnClickListener
            }

            val eveningUsageFromUI : Double  = etEveningUsage.toDouble()

//            ● Morning rates are $0.132 per kwh
            // morning usage amount before round off, which is the actual morning usage
            val actualMorningUsageAmount : Double = morningUsageFromUI * 0.132
            // after rounding off to 2 decimal points
            val morningUsageAmount : Double = String.format("%.2f", actualMorningUsageAmount).toDouble()

//            ● Evening rates are: $0.094 per kwh
            // evening usage amount before roundoff - which is the actual one
            val actualEveningUsageAmount : Double = eveningUsageFromUI * 0.094
            // after rounding off to 2 decimal points
            val eveningUsageAmount : Double = String.format("%.2f", actualEveningUsageAmount).toDouble()

            // Total usage charge : sum of morning usage and evening usage
            val totalUsageCost = String.format("%.2f", (morningUsageAmount + eveningUsageAmount)).toDouble()

            // calculate environmental rebate
            var environmentalRebate : Double = 0.0
            if (this.binding.swRenewableEnergySource.isChecked) {
                environmentalRebate = String.format("%.2f", (totalUsageCost * 0.09)).toDouble()
            } else {
                environmentalRebate = 0.0
            }

            // calculate subtotal
            // subtotal = total usage  - environmental rebate
            val subtotalBeforeRoundOff = totalUsageCost - environmentalRebate
            // subtotal after round off to 2 decimal points
            val subtotal = String.format("%.2f", (subtotalBeforeRoundOff)).toDouble()

            // calculate sales tax
            // sales tax is 13% of subtotal
            // sales tax before round off
            val taxBeforeRoundOff = subtotal * 0.13
            // tax after round off to 2 decimal points
            val tax = String.format("%.2f", (taxBeforeRoundOff)).toDouble()

            // calculate the amount user have to pay
            // ((morning electricity usage cost + evening electricity usage cost) - environmental rebate) + sales tax
            // or,subtotal + tax
            // amount to pay before round off
            val amountToPayBeforeRoundOff = subtotal + tax
            // amount to pay after round off
            val amountToPay = String.format("%.2f", (amountToPayBeforeRoundOff)).toDouble()

            // Outputs the receipt to the screen
            binding.receiptHeader.setText("Charge Breakdown")

            var receipt : String = "Morning Usage Cost: $${morningUsageAmount}\n" +
                    "Evening Usage Cost: $${eveningUsageAmount}\n" +
                    "Total usage charge: $${totalUsageCost}\n" +
                    "Environmental rebate: $${environmentalRebate}\n" +
                    "Subtotal: ${subtotal}\n" +
                    "Tax: $${tax}\n"

            binding.receiptBody.setText(receipt)

            binding.receiptAmountPay.setText("YOU MUST PAY: $${amountToPay}")
            binding.receiptAmountPay.setBackgroundColor(0xffFFC107.toInt())

        }

        // When the RESET button is pressed, clear all form fields and receipts.
        this.binding.btnReset.setOnClickListener {
            // Clear all form fields and receipts from the screen
            binding.etMorningUsage.setText("")
            binding.etEveningUsage.setText("")
            binding.swRenewableEnergySource.setChecked(false)
            binding.receiptHeader.setText("")
            binding.receiptBody.setText("")
            binding.receiptAmountPay.setText("")
            binding.receiptAmountPay.setBackgroundColor(Color.WHITE)
            binding.errorMessage.setText("")
            binding.errorMessage.setBackgroundColor(Color.WHITE)

        }
    }
}
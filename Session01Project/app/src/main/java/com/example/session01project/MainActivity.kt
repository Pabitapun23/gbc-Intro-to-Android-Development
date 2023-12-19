package com.example.session01project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // this function will execute right before the UI is displayed on the screen
    // setup your click functions in this function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Get a reference to the button
        // here, R is reference to the "res" folder in our project
        val btn:Button = findViewById<Button>(R.id.btnPressMe)

        // 2. Add a click handler to the button
        btn.setOnClickListener {
            // 3. Within the click handler code, write your logic.

            // 3a. Get the values from the Textboxes.
            // - get the textboxes
            val etName:EditText = findViewById<EditText>(R.id.etName)
            val etAge:EditText = findViewById<EditText>(R.id.etAge)

           // - Use built in properties to get the content of the textbox.
            val nameFromUI:String = etName.text.toString()
            val ageFromUI:String = etAge.text.toString()

            // 3b. Convert the age to a number.
            // everything from textbox has to convert into string first then into int.
            val age:Int = ageFromUI.toInt()

            // 3c. Calculate the age in 5 years.
            val ageIn5Years = age + 5

            // 3d. Output the name & new age in the screen
            // - find the textview
            val tvResults:TextView = findViewById<TextView>(R.id.tvResults)
            tvResults.setText("Hello ${nameFromUI}, in 5 years, you will be ${ageIn5Years} years old.")

        }


        val btnCalculate:Button = findViewById<Button>(R.id.btnCalculate)
        btnCalculate.setOnClickListener {
            val etProducePrice:EditText = findViewById<EditText>(R.id.etProducePrice)

            val priceFromUI:String = etProducePrice.text.toString()

            val priceAsNumber = priceFromUI.toDouble()

            val taxAmount = priceAsNumber * 0.13

            val finalPrice = priceAsNumber + taxAmount

            // output to screen
            val tvPriceDetails:TextView = findViewById<TextView>(R.id.tvPriceDetails)

            val output = "Price: ${priceAsNumber}\n" +
                         "Tax Amount: ${taxAmount}\n" +
                         "Final Price: ${finalPrice}"

            tvPriceDetails.setText(output)
        }

    }
}
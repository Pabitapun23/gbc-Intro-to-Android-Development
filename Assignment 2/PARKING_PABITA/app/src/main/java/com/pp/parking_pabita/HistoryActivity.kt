package com.pp.parking_pabita

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pp.parking_pabita.databinding.ActivityHistoryBinding

// Screen #2 should receive the list of purchases from Screen #1.

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    // Made the adapter global
    private lateinit var adapter:ParkingFeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_history)

        this.binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // get the array from the intent
        val receivedData = intent.getStringArrayListExtra("PARKING_RECEIPT_EXTRA")

        // attempt to convert the array back to a list
        var myReceiptsList:MutableList<String> = mutableListOf()
        // if data from intent contains an array, then:
        if (receivedData != null) {
            // convert the array back to a mutable list.
            myReceiptsList = receivedData.toMutableList()
        }

        // to send a function to constructor's parameter
        this.adapter = ParkingFeeAdapter(myReceiptsList)
        binding.rv.adapter = adapter

        // required
        binding.rv.layoutManager = LinearLayoutManager(this)

        // add a line between each item in the list
        binding.rv.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        // back button - which navigates back to the 1st screen
        binding.btnBack.setOnClickListener {
            finish()
        }

    }

}
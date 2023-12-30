package com.pp.parking_pabita

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ParkingFeeAdapter (
    var receipts:List<String>,
) : RecyclerView.Adapter<ParkingFeeAdapter.ParkingFeeViewHolder>() {

    inner class ParkingFeeViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {
    }

    // tell the function which layout file to use for each row of the recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingFeeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_layout_receipt, parent, false)
        return ParkingFeeViewHolder(view)
    }

    // how many items are in the list
    override fun getItemCount(): Int {
        return receipts.size
    }

    // In a single row, what data goes in the <TextView>
    override fun onBindViewHolder(holder: ParkingFeeViewHolder, position: Int) {
        // Get the TextView from the row layout
        val tvLabel = holder.itemView.findViewById<TextView>(R.id.tvReceiptRow1)
        // Set its value
        tvLabel.text = "${receipts.get(position)}"
    }

}
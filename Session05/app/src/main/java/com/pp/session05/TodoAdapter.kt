package com.pp.session05

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// 1. what is the list of data that you want to display
// 2. what is the layout you should use

class TodoAdapter(var todos:List<String>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {

    }

    // tell the function which layout file to use for each row of the recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.row_layout_todo, parent, false)
        return TodoViewHolder(view)
    }

    // how many items are in the list
    override fun getItemCount(): Int {
        return todos.size
    }

    // In a single row, what data goes in the <TextView>?
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        // 1. Get the TextView from the row layout
        val tvLabel = holder.itemView.findViewById<TextView>(R.id.tvRowLine1)
        // 2. Set its value
        tvLabel.text =  "Task : ${todos.get(position)}"

    }

}
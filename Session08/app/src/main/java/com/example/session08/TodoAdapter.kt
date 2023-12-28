package com.example.session08

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// 1. Update the constructor to indicate that
// it should accept a function
// : () --> indicate the data type of any parameter
// -> Unit --> indicates that the function returns no data
class TodoAdapter(
    var todos:List<String>,
    private val editBtnClickHandler: (Int) -> Unit, // Unit - return type
    private val deleteBtnClickHandler: (Int) -> Unit,
    private val rowClickHandler: (Int) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView) {
        init {
            // get the edit button
            val btnEdit = itemView.findViewById<Button>(R.id.btnEdit)
            // get the delete button
            val btnDelete = itemView.findViewById<Button>(R.id.btnDelete)

            // associate the button with the click handler function
            // from the main activity
            btnEdit.setOnClickListener {
                // adapterPosition = a built in variable
                // that contains the position of the row
                // the person clicked on
                editBtnClickHandler(adapterPosition)
            }

            btnDelete.setOnClickListener {
                deleteBtnClickHandler(adapterPosition)
            }

            // get the entire row (itemView = the entire row)
            itemView.setOnClickListener {
                rowClickHandler(adapterPosition)
            }
        }
    }

    // tell the function which layout file to use for each row of the recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_layout_todo, parent, false)
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
        tvLabel.text = "Task: ${todos.get(position)}"
    }

}
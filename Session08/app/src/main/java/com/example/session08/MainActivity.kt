package com.example.session08

import android.R
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.session08.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.text.FieldPosition

// adapter - a class that's responsible to populate the recycler view

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Made the todos list global
    // List = a list that cannot be modified(you cannot add, remove, or change the contents of the list)
    // MutableList = a list that CAN add new items/ remove old items
    private var todosList:MutableList<String>
            = mutableListOf("Walk the dog", "Do homework", "Eat dinner")

    // Made the adapter global
    private lateinit var adapter:TodoAdapter

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            // result.data == Intent() object we created in the EditScreenActivity's btnUpdatefunction
            if (result.data != null) {
                val dataFromScreen2 : Intent = result.data!!
                val updatedText = dataFromScreen2.getStringExtra("UPDATED_TEXT_EXTRA")
                val pos = dataFromScreen2.getIntExtra("POSITION_EXTRA", 0)

                // update the data source with the next text
                todosList.set(pos, updatedText!!)

                Log.d("TAG", todosList.toString())

                // notify the adapter that the list has changed
                adapter.notifyDataSetChanged()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set up adapter
        // - There is special Kotlin syntax you must use
        // to send a function to constructor's parameter
        this.adapter = TodoAdapter(todosList, {pos -> editButtonClicked(pos)}, {pos-> deleteButtonClicked(pos)}, {pos-> rowClicked(pos)})
        binding.rv.adapter = adapter

        // required
        binding.rv.layoutManager = LinearLayoutManager(this)

        // add a line between each item in the list (gray line)
        binding.rv.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )


        // Here, we write codes that is applicable for all elements

        // add a button click handler for the ADD button
        binding.btnAddItem.setOnClickListener {
            // 1. get the new todo from the textbox

            val todoFromUI = binding.etTodoDescription.text.toString()
            // optionally: do validation

            // 2. add it to the list
            todosList.add(todoFromUI)

            binding.etTodoDescription.setText("")

            // 3. call the adapter's notifyDataSetChanged to tell the adapter to
            // redraw the RV
            adapter.notifyDataSetChanged()

        }

        binding.btnDeleteAll.setOnClickListener {
            todosList.clear()
            adapter.notifyDataSetChanged()
        }

        binding.btnReset.setOnClickListener {
            // delete everything
            todosList.clear()

            // add back the original items
            todosList.addAll(listOf("Walk the dog", "Do homework", "Eat dinner"))

            // notify the adapter
            adapter.notifyDataSetChanged()
        }

    }

    // click handler for the RV's "edit" button
    private fun editButtonClicked(rowPosition: Int) {
        // When the person clicks the edit button, what should happen?
        val snackbar = Snackbar.make(binding.rootLayout, "EDIT BUTTON for row ${rowPosition} clicked", Snackbar.LENGTH_LONG)
        snackbar.show()

        val intent = Intent(this, EditScreenActivity::class.java)
        intent.putExtra("ROW_POS_EXTRA", rowPosition)

        // send the text of the todo to screen #2
        intent.putExtra("ORIGINAL_TODO_EXTRA", todosList.get(rowPosition))
//        startActivity(intent)

        startForResult.launch(intent)

    }

    // click handler for the RV's "delete" button
    private fun deleteButtonClicked(rowPosition: Int) {
        // When the person clicks the delete button, what should happen?
        val toast = Toast.makeText(this, "DELETE BUTTON for row ${rowPosition} clicked", Toast.LENGTH_LONG)
        toast.show()

        // delete the selected item from the list
        todosList.removeAt(rowPosition)

        adapter.notifyDataSetChanged()

    }

    // click handler for the RV's entire row
    private fun rowClicked(rowPosition: Int) {
        // When the person clicks the edit button, what should happen?
        val snackbar = Snackbar.make(binding.rootLayout, "+++ ENTIRE ROW ${rowPosition} CLICKED +++", Snackbar.LENGTH_LONG)
        snackbar.show()
    }

}
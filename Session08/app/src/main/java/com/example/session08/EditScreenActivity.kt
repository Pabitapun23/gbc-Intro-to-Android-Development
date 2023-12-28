package com.example.session08

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.session08.databinding.ActivityEditScreenBinding

class EditScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditScreenBinding

    private var rowPosition:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_edit_screen)

        this.binding = ActivityEditScreenBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // get the intent data
        if(intent != null) {
            this.rowPosition = intent.getIntExtra("ROW_POS_EXTRA", -1)
            val originalTodo = intent.getStringExtra("ORIGINAL_TODO_EXTRA")

            // populate the edit text
            binding.etUpdatedTodo.setText(originalTodo + " ${rowPosition}")
        }


        binding.btnUpdate.setOnClickListener {
            // get the item in the text box
            val updatedText = binding.etUpdatedTodo.text.toString()

            // define the data you want to send "backwards" to the previous screen
            // use this intent to send them back to the previous screen with some data
            val intent = Intent()
            // send back the position of the todo in the list to the previous screen
            intent.putExtra("POSITION_EXTRA", rowPosition)
            // send the updated textbox text to the previous screen
            intent.putExtra("UPDATED_TEXT_EXTRA", updatedText)
            // required
            setResult(Activity.RESULT_OK, intent)
            // programmatically navigate them backwards
            finish()
        }
    }

//    1/ Launch the Edit Screen using a start for result
//
//    StartActivity() —>  Navigate the person to the next screen
//    .launch() → Navigates the person to the screen, with the expectation that they will come back to your original screen with some data
//    (navigate forward, navigate backwards with some data)

}
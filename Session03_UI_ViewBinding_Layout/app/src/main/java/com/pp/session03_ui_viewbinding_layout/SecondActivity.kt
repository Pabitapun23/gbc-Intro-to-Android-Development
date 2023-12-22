package com.pp.session03_ui_viewbinding_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.pp.session03_ui_viewbinding_layout.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_second)

        // for every new binding class, we have to do this.
        this.binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // option - 3 : to get the selected inputs from the radiobutton
        this.binding.btnRegister.setOnClickListener(this)
        this.binding.rgroupMembership.setOnCheckedChangeListener { radioGroup, selectedRadioButtonID ->
            val selectedOption : RadioButton = findViewById(selectedRadioButtonID)
            val selectedMembership = selectedOption.text.toString()

            Toast.makeText(this@SecondActivity,
                "$selectedMembership",
                Toast.LENGTH_SHORT).show()

            this.binding.tvOutput.text = "$selectedMembership"
        }

    }

    // fun onCreate() calls only one time - this delays the launching of event or operation since it calls only one time.
    // So, if we write anything we need quickly, we should do it in different function and avoiding it doing in onCreate().

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_register -> {
                // perform operation for button click
                this.registerUser()
            }
        }
    }

    private fun registerUser() {
        if(this.binding.editName.text.isNullOrEmpty()) {
            this.binding.editName.setError("Name cannot be empty")
        } else {
            val userName : String = this.binding.editName.text.toString()
            this.binding.tvOutput.text = userName

            // option - 1
//            // get the id of selected radio button from group
//            val selectedRadioButtonID = this.binding.rgroupMembership.checkedRadioButtonId
//            // obtain the instance of RadioButton using the id
//            val selectedRadioButton : RadioButton = findViewById(selectedRadioButtonID)
//            // obtain the text of the selected radio button
//            val selectedMembershipType = selectedRadioButton.text.toString()

            // option - 2
            val selectedRadioButtonID = this.binding.rgroupMembership.checkedRadioButtonId
            var selectedMembershipType = ""
            var membershipAmount = 0.0

            when(selectedRadioButtonID) {
                R.id.rbtn_gold -> {
                    selectedMembershipType = "Gold"
                    membershipAmount = 50.0
                }
                R.id.rbtn_silver -> {
                    selectedMembershipType = "Silver"
                    membershipAmount = 35.0
                }
                R.id.rbtn_platinum -> {
                    selectedMembershipType = "Platinum"
                    membershipAmount = 60.0
                }
            }

            this.binding.tvOutput.text = "${this.binding.tvOutput.text}" +
                    "\nMembershipType : ${selectedMembershipType}" +
                    "\nMembershipAmount : $${membershipAmount}"
        }
    }
}
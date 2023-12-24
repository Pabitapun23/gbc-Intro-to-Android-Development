package com.pp.session04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.pp.session04.databinding.ActivityMainBinding
import com.pp.session04.databinding.ActivitySecondaryBinding

class SecondaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondaryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivitySecondaryBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // 1. make an list of your buttons
        val buttonList:List<Button> =
            listOf<Button>(binding.btnPressMe1, binding.btnPressMe2, binding.btnPressMe3)


        binding.btnDisableAll.setOnClickListener {
            // loop through your list of buttons
            for (currButton in buttonList) {
                // for each button, disable it
                currButton.setEnabled(false)
            }
            // option 2:
//            for (i in 0..buttonList.size) {
//                buttonList[i].setEnabled(false)
//            }



        }
        binding.btnResetAll.setOnClickListener {
            for (currButton in buttonList) {
                // for each button, disable it
                currButton.setEnabled(true)
            }
        }

    }
}


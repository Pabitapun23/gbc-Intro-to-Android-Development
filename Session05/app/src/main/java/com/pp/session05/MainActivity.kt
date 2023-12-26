package com.pp.session05

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pp.session05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // spinner's data source: list of data to show in your spinner
        val citiesList : List<String> = listOf("Cairo", "Paris", "Shanghai", "Sao Paolo")
        val sportsList : List<String> = listOf("Basketball", "Football", "Tennis", "Volleyball", "Hockey")

        // create an adapter
        // - an adapter is a special object that is used to display information in the spinner
        // param #2 = the layout for each option in the dropdown
        // android comes with default layouts for the spinner option
        // android.R.layout.simple_spinner_item

        val citiesAdapter : ArrayAdapter<String> = ArrayAdapter<String>(this, R.layout.simple_spinner_item, citiesList)
        val sportsAdapter : ArrayAdapter<String> = ArrayAdapter<String>(this, R.layout.simple_spinner_item, sportsList)

        // associate the spinner with the adapter
        binding.spinner1.adapter = citiesAdapter
        binding.spinner2.adapter = sportsAdapter

        // click on an item in the spinner
        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val snack = Snackbar.make(binding.rootlayout, "Clicked on item ${position}, ${citiesList.get(position)}", Snackbar.LENGTH_SHORT)
                snack.show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        // click handler for the button
        binding.btnSubmit.setOnClickListener {
            // get the selected spinner item (selected city)
            val selectedPos : Int = binding.spinner1.selectedItemPosition
            val selectedCity : String = citiesList.get(selectedPos)

            // get the selected sport
            val selectedSport = sportsList.get(binding.spinner2.selectedItemPosition)

            // output it to the screen
            val output = "Selected city is: ${selectedCity}\n" +
                    "Selected sport is: ${selectedSport}"

            binding.tvResults.setText(output)

        }

//        Exercise:
//        Add another dropdown that shows a list of sports
//        When the user presses SUBMIT, show the SELECTED CITY and the SELECTED SPORT in the user interface


        // Todo App
//        var todosList:List<String> = listOf("Walk the dog", "Do homework", "Eat dinner")
//        var adapter:TodoAdapter = TodoAdapter(todosList)
//        binding.rv.adapter = adapter
//
//        // required
//        binding.rv.layoutManager = LinearLayoutManager(this)
//
//        // add a line between each item in the list
//        binding.rv.addItemDecoration(
//            DividerItemDecoration(
//                this,
//                LinearLayoutManager.VERTICAL
//            )
//        )

        var todosList:List<String> = listOf("Walk the dog", "Do homework", "Eat dinner")
        var adapter:TodoAdapter = TodoAdapter(todosList)
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




    }
}
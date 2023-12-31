package com.example.fruitlistapp.screens

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fruitlistapp.databinding.ActivityScreen2Binding
import com.example.fruitlistapp.models.Fruit
import com.example.fruitlistapp.models.Student
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Screen2Activity : AppCompatActivity() {

    lateinit var binding:ActivityScreen2Binding

    // class properties: sharedpreferences
    lateinit var sharedPreferences: SharedPreferences

    // - this is needed to put sharedpreferences in editing mode so we can "save"
    lateinit var prefEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize shared preferences
        // for NAME - you decide what name to put there
        this.sharedPreferences = getSharedPreferences("MY_APP_PREFS", MODE_PRIVATE)

        // configure shared preferences to work in "editing mode"
        // "editing mode" is required when you want to "save" data to shared preferences
        this.prefEditor = this.sharedPreferences.edit()
        // prefEditor - it's only for saving datas

        // click handlers
        binding.btnReset.setOnClickListener {
            // delete all values in SharedPreferences
//            prefEditor.clear()

            // delete a single value (not all values)
            prefEditor.remove("KEY_NAME")

            // save the changes
            prefEditor.apply()

            Snackbar.make(binding.root, "Data erased!", Snackbar.LENGTH_LONG).show()
            // erase anything that is displayed in the results
            binding.results.setText("")

        }

        binding.btnSave.setOnClickListener {
            // get the name from the UI
            val nameFromUI:String = binding.etName.text.toString()
            // get the age from the UI
            val ageFromUI:Int = binding.etAge.text.toString().toInt()
            val isPGFromUI:Boolean = binding.swIsPg.isChecked

            // insert the data
            // create the key-value pair
            // key = KEY_NAME
            // value = Peter
            this.prefEditor.putString("KEY_NAME", nameFromUI)
            this.prefEditor.putInt("KEY_AGE", ageFromUI)
            this.prefEditor.putBoolean("KEY_IS_PG", isPGFromUI)

//            // save a fruit
//            // get instance of the Gson library
//            val gson = Gson()
//            // - get an object
//            val fruitToSave = Fruit("Coconut", "Makes a refreshing drink", "coconut")
//            // - use GSON library to convert fruit to a string
//            val fruitAsString = gson.toJson(fruitToSave)
//
//            // make a key-value pair
//            this.prefEditor.putString("KEY_FRUIT", fruitAsString)

//            Exercise:
//
//            Add a Student class to the application   	(models/Student.kt)
//            When the SAVE button is pressed, get the name, age, isPG and save it to a Student object
//            Save the Student object to SharedPreferences
//            Write the code to retrieve the Student Object from Shared Preferences and output it to the screen
//            Name: Peter
//            Age: 83
//            Is Post Graduate: true/false

            // TODO: Update to save a Student object
            // save a student
            // get instance of the Gson library
            val gson = Gson()
            // - get an object
            val studentToSave = Student(nameFromUI, ageFromUI, isPGFromUI)
            // - use GSON library to convert student to a string
            val studentAsString = gson.toJson(studentToSave)

            // make a key-value pair
//            this.prefEditor.putString("KEY_FRUIT", fruitAsString)
            this.prefEditor.putString("KEY_STUDENT", studentAsString)



            // TODO: Save a list of fruits
            var fruitsList:List<Fruit> = listOf<Fruit>(
                Fruit("Apple Pie", "Great for pies and juices", "apple"),
                Fruit("Banana", "Curved yellow fruit on tree", "banana"),
                Fruit("Blackberry", "Lots of antioxidents, very healthy", "blackberry"),
            )

            // save fruits
            //val gson = Gson()
            val jsonFruitBasket = gson.toJson(fruitsList)

            // -key value pair
            prefEditor.putString("KEY_FRUIT_BASKET", jsonFruitBasket)



            // commit your changes (save your key-value pairs to memory)
            this.prefEditor.apply()

            // show a confirmation message
            Snackbar.make(binding.root, "Data saved!", Snackbar.LENGTH_LONG).show()

            // show datas on the screen
//            binding.results.setText("Name: ${nameFromUI}\n" +
//                    "Age: ${ageFromUI}\n" +
//                    "PostGraduate? ${isPGFromUI}")
        }

        binding.btnRetrieve.setOnClickListener {
//            // get the name from SharedPreferences
//            // - parameter 2 = what should we return if "KEY_NAME" does not exist
//            val nameFromSP = sharedPreferences.getString("KEY_NAME", "not available")
//            val ageFromSP = sharedPreferences.getInt("KEY_AGE", -1)
//            val pgFromSP = sharedPreferences.getBoolean("KEY_IS_PG", false)

//            binding.results.setText("Name: ${nameFromSP}\n" +
//                    "Age: ${ageFromSP}\n" +
//                    "PostGraduate? ${pgFromSP}")

//            // get the Fruit String that is stored in SP
//            val gson = Gson()
//            val fruitFromSP = sharedPreferences.getString("KEY_FRUIT", "")
//            if(fruitFromSP != "") {
//                // convert the string back into a fruit object
//                val gson = Gson()
//                val fruitAsObject:Fruit = gson.fromJson(fruitFromSP, Fruit::class.java)
//                binding.results.setText(fruitAsObject.toString())
//            }

            // get the student from the SP
            // get the Student String that is stored in SP
            val studentFromSP = sharedPreferences.getString("KEY_STUDENT", null)

            if(studentFromSP != null) {
                // convert the string back into a Student object
                val gson = Gson()
                val studentObject:Student = gson.fromJson(studentFromSP, Student::class.java)
//                binding.results.setText(studentObject.toString())
                binding.results.setText("Name: ${studentObject.name}\nAge:${studentObject.age}\nIs PG? ${studentObject.isPG}")

            }

            // TODO: Get our fruits list
            val fruitsFromSP = sharedPreferences.getString("KEY_FRUIT_BASKET", null)
            if (fruitsFromSP != null) {
                // convert the string back into a fruit object
                val gson = Gson()
                // define what type of data we should convert the string back to
                val typeToken = object : TypeToken<List<Fruit>>() {}.type
                // convert the string back to a list
                val fruitsList = gson.fromJson<List<Fruit>>(fruitsFromSP, typeToken)

                var output = ""
                for (currFruit in fruitsList) {
                    output += "${currFruit.name}\n" +
                            "Desc: ${currFruit.desc}\n" +
                            "-----------\n"
                }

                binding.results.setText(output)
            }

        }

        // TODO: Try saving the age (getInt/putInt) $ switch (getBoolean/putBoolean)

    }
}
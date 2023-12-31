package com.example.fruitlistapp.models

// shortcut for defining properties & constructor in one line
class Student(
    var name: String,
    var age: Int,
    var isPG: Boolean
) {

    // debug
    override fun toString(): String {
        return "Student:\n" +
                "Name: $name\n" +
                "Age: $age\n" +
                "Is Post Graduate: $isPG"
    }

}
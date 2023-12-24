package com.pp.session04

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.pp.session04.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // B. 1. add counter variables
    private var gamesPlayed = 0
    private var playerWinCounter = 0
    private var computerWinCounter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        // EXERCISE
        // Add a button to our layout (LinearLayout)

//        this.binding.btnRollDice.setOnClickListener {
////            val toast = Toast.makeText(this@MainActivity,
////                "HELLO",
////                Toast.LENGTH_SHORT).show()
//
//
//            val snackbar = Snackbar.make(binding.rootLayout,
//                "Hello!", Snackbar.LENGTH_LONG)
//            snackbar.show()
//        }

        this.binding.btnRollDice.setOnClickListener {

            // A. Dice Game 1:
            //A. 1. Get the value from the textbox
            // game logic
            val etPlayerNumber : String = this.binding.etPlayerGuess.text.toString()

            // error handling
            if (etPlayerNumber.isEmpty()) {
                val snackbar  = Snackbar.make(binding.rootLayout, "ERROR: Must enter a value", Snackbar.LENGTH_LONG)
                snackbar.show()
                return@setOnClickListener
            }

            // trying not actual
//            if (this.binding.etPlayerGuess.text.isNullOrEmpty()) {
//                this.binding.etPlayerGuess.setError("Name cannot be empty")
//            }



            val playerGuessFromUI : Int = etPlayerNumber.toInt()   // convert player number to int

            if(playerGuessFromUI > 6 || playerGuessFromUI < 1) {
                val snackbar = Snackbar.make(binding.rootLayout,
                    "ERROR: Must enter value between 1-6", Snackbar.LENGTH_LONG)
                snackbar.show()
                return@setOnClickListener
            }

            // 2. Generate a random number between 1-6
            val computerGuess = (1..6).random()

            // 3. Calculate who wins
            var winner = ""
            if(playerGuessFromUI > computerGuess) {
                winner = "Player"
                playerWinCounter += 1
            } else {
                winner = "Computer"
                computerWinCounter = computerWinCounter + 1
            }

            // 4. Show the winner in the snackbox
//            val snackbar = Snackbar.make(binding.rootLayout,
//                "Player: ${playerGuessFromUI}, Computer: ${computerGuess}, Winner: ${winner}", Snackbar.LENGTH_LONG)
//            snackbar.show()

            // 5. Update the dice image
            // Programmatically generate the image name
            val imagename = "dice0${computerGuess}"
            val res = resources.getIdentifier(imagename, "drawable", this.packageName)
            this.binding.diceImg.setImageResource(res)

        //   B.     Dice Game Part 2:
//        Update the application so the winner is determined based on the best of 3 games
//        After each game, the application should show the results of game
//        After 3 games, disable the roll dice button
//        The user must press the RESTART button to start over
//                When the RESTART button is pressed:
//        Enable the roll dice button
//        Clear any messages

            // B. 2. update the total number of games played
            gamesPlayed = gamesPlayed + 1

            // Outputs the results to the screen
            var output : String = "Game #${gamesPlayed}\n" +
                    "Player: ${playerGuessFromUI}, Computer: ${computerGuess}, Winner: ${winner}\n"

            // a. get the current text in the label
            var curr = binding.gameResults.text.toString()  //CharSequence
            // b. append on your next text
            var updated = curr + output
            // c. update the label with your new text
            binding.gameResults.setText(updated)

            // 3. check if its game over (games played == 3)
            if(gamesPlayed==3) {
                // 4. game over
                // TODO : Disable the button
                binding.btnRollDice.setEnabled(false)

                // TODO : Show the winner?
                var overallWinner = ""
                if(playerWinCounter > computerWinCounter) {
                    overallWinner = "PLAYER"
                } else {
                    overallWinner = "COMPUTER"
                }

                val snackbar  = Snackbar.make(binding.rootLayout, "GAME OVER! Winner after 3 games: ${overallWinner}", Snackbar.LENGTH_LONG)
                snackbar.show()
            }

        }

        this.binding.btnReset.setOnClickListener {
            // UI changes : enable the press button
            binding.btnRollDice.setEnabled(true)
            // TODO: Clear any messages on the screen about prev game history
            binding.gameResults.setText("")  // empties game results
            // binding.gameResults.text = "" //option 2 for empting game results

            // LOGIC changes: reset all counters
            gamesPlayed = 0
            playerWinCounter = 0
            computerWinCounter = 0

        }

    }

}
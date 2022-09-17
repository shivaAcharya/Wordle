package com.example.wordle

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.wordle.FourLetterWordList.getRandomFourLetterWord
import java.security.KeyStore


class MainActivity : AppCompatActivity() {
    var ansWord = getRandomFourLetterWord()
    var guess = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val finalAns = findViewById<TextView>(R.id.textViewAns)
        val textWord1 = findViewById<TextView>(R.id.txtViewWord1)
        val textWord2 = findViewById<TextView>(R.id.txtViewWord2)
        val textWord3= findViewById<TextView>(R.id.txtViewWord3)
        val textWord1Check = findViewById<TextView>(R.id.txtViewWord1Check)
        val textWord2Check = findViewById<TextView>(R.id.txtViewWord2Check)
        val textWord3Check = findViewById<TextView>(R.id.txtViewWord3Check)
        //val textGuess1 = findViewById<TextView>(R.id.txtViewGuess1)
        val textGuess2 = findViewById<TextView>(R.id.txtViewGuess2)
        val textGuess3 = findViewById<TextView>(R.id.txtViewGuess3)
        val textGuess1Check = findViewById<TextView>(R.id.txtViewGuess1Check)
        val textGuess2Check = findViewById<TextView>(R.id.txtViewGuess2Check)
        val textGuess3Check = findViewById<TextView>(R.id.txtViewGuess3Check)

        // Set texView invisible
        textWord1.isVisible = false
        textWord2.isVisible = false
        textWord3.isVisible = false
        textWord1Check.isVisible = false
        textWord2Check.isVisible = false
        textWord3Check.isVisible = false
        textGuess2.isVisible = false
        textGuess3.isVisible = false
        textGuess1Check.isVisible = false
        textGuess2Check.isVisible = false
        textGuess3Check.isVisible = false
        finalAns.isVisible = false


        val guessButton = findViewById<Button>(R.id.btnGuess)


        guessButton.setOnClickListener {

            // Retrieve Value from EditText
            val simpleEditText = findViewById<View>(R.id.et_simple) as EditText
            val strValue = simpleEditText.text.toString().uppercase()

            val checkGuessWord = checkGuess(ansWord, strValue)
            simpleEditText.text.clear()

            guess++

            // First Guess
            if (guess == 1){
                textWord1.isVisible = true
                textWord1.text = strValue

                textGuess1Check.isVisible = true

                textWord1Check.isVisible = true
                textWord1Check.text = checkGuessWord
            }

            // Second Guess
            if (guess == 2){
                textWord2.isVisible = true
                textWord2.text = strValue

                textGuess2.isVisible = true
                textGuess2Check.isVisible = true

                textWord2Check.isVisible = true
                textWord2Check.text = checkGuessWord
            }

            // Third Guess
            if (guess == 3){
                textWord3.isVisible = true
                textWord3.text = strValue

                textGuess3.isVisible = true
                textGuess3Check.isVisible = true

                textWord3Check.isVisible = true
                textWord3Check.text = checkGuessWord

                finalAns.isVisible = true
                finalAns.text = ansWord

                guessButton.alpha = .5f
                guessButton.isClickable = false

            }



            // https://stackoverflow.com/questions/1109022/how-to-close-hide-the-android-soft-keyboard-programmatically

            // Only runs if there is a view that is currently focused
            this.currentFocus?.let { view ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(view.windowToken, 0)
            }

        }

    }
    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(wordToGuess: String, guess: String): String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }

}

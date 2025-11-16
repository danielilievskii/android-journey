package com.example.rolldice

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
//import android.graphics.Color
import com.example.rolldice.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    private var dice1Score: Int = 0
    private var dice2Score: Int = 0
    private var rounds = mutableListOf<Int>()

    companion object {
        const val DICE1_ERROR = "You cannot roll this dice again. Roll dice 2."
        const val DICE2_ERROR = "You cannot roll this dice again. Roll dice 1."
        const val WIN_MESSAGE = "Congratulations! You won."
        const val LOSE_MESSAGE = "Unfortunately you lost! Better luck next time."
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.root.setBackgroundColor(Color.RED)
            } else {
                binding.root.setBackgroundColor(Color.WHITE)
            }
        }

        binding.btnScoreboard.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("ROUNDS", ArrayList(rounds))
            }
            startActivity(intent)
        }

        val diceImages = listOf(R.drawable.casino_24px, R.drawable.ifl_24px)
        val rollCount = 10
        val interval = 300L

        binding.btnDice1.setOnClickListener {
            clearErrorMessage()

            if(dice1Score != 0) {
                if(dice2Score == 0) {
                    setErrorMessage(DICE1_ERROR)
                }
                return@setOnClickListener
            }

            for (i in 0..rollCount) {
                binding.icDice1.postDelayed({
                    binding.icDice1.setImageResource(diceImages[i % diceImages.size])
                }, interval * i)
            }

            binding.icDice1.postDelayed({
                dice1Score = rollDice()
                binding.tvDice1Score.text = dice1Score.toString()
                binding.icDice1.isClickable = false

                if (dice2Score != 0) {
                    scoreRound()
                }

            }, interval * rollCount)
        }

        binding.btnDice2.setOnClickListener {
            clearErrorMessage()

            if(dice2Score != 0) {
                if(dice1Score == 0) {
                    setErrorMessage(DICE2_ERROR)
                }
                return@setOnClickListener
            }

            for (i in 0 until rollCount) {
                binding.icDice2.postDelayed({
                    binding.icDice2.setImageResource(diceImages[i % diceImages.size])
                }, interval * i)
            }

            binding.icDice2.postDelayed({
                dice2Score = rollDice()
                binding.tvDice2Score.text = dice2Score.toString()
                binding.icDice2.isClickable = false

                if (dice1Score != 0) {
                    scoreRound()
                }

            }, interval * rollCount)
        }

        binding.btnReset.setOnClickListener {
            resetRound()
        }
    }

    fun clearErrorMessage() {
        binding.tvError.text = ""
    }

    fun clearResultMessage() {
        binding.tvResult.text = ""
    }

    fun setErrorMessage(errorMessage: String) {
        binding.tvError.text = errorMessage
    }

    fun scoreRound() {
        val finalScore = getScore()

        if(finalScore >= 7) {
            binding.tvResult.text = WIN_MESSAGE
        } else {
            binding.tvResult.text = LOSE_MESSAGE
        }

        rounds.add(finalScore)
    }

    fun rollDice() : Int {
        return (1..6).random()
    }

    fun getScore() : Int {
        return dice1Score + dice2Score
    }

    fun resetRound() {
        dice1Score = 0
        binding.tvDice1Score.text = dice1Score.toString()
        binding.btnDice1.isClickable = true

        dice2Score = 0
        binding.tvDice2Score.text = dice2Score.toString()
        binding.btnDice2.isClickable = true

        clearErrorMessage()
        clearResultMessage()
    }
}

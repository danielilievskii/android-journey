package com.example.rockpaperscissors

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.rockpaperscissors.databinding.ActivityMainBinding

enum class Strengths {
    ROCK, PAPER, SCISSORS
}

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    //    private val strengths = listOf(Strengths.entries.toTypedArray())
    private var userStrength: Strengths = Strengths.ROCK
    private var userScore: Int = 0
    private var computerScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.icUserRockChoice.setOnClickListener {
            binding.icUserChoice.setImageResource(R.drawable.landslide_24px)
            userStrength = Strengths.ROCK
            playGame()
        }

        binding.icUserPaperChoice.setOnClickListener {
            binding.icUserChoice.setImageResource(R.drawable.article_24px)
            userStrength = Strengths.PAPER
            playGame()
        }

        binding.icUserScissorsChoice.setOnClickListener {
            binding.icUserChoice.setImageResource(R.drawable.content_cut_24px)
            userStrength = Strengths.SCISSORS
            playGame()
        }

        binding.button.setOnClickListener {
            val intent = Intent(this, SecondActivty::class.java).apply {
                putExtra("USER_SCORE", userScore)
                putExtra("COMPUTER_SCORE", computerScore)
            }
            startActivity(intent)
        }

    }

    private fun playGame() {
        val computerStrength: Strengths = getComputerStrength()

        val computerStrengthImageInt = when(computerStrength) {
            Strengths.PAPER -> R.drawable.article_24px
            Strengths.SCISSORS -> R.drawable.content_cut_24px
            Strengths.ROCK -> R.drawable.landslide_24px
        }

        binding.icComputerChoice.setImageResource(computerStrengthImageInt)

        val result = when {
            userStrength == computerStrength -> "Tie"
            (userStrength == Strengths.ROCK && computerStrength == Strengths.SCISSORS)
                    || userStrength == Strengths.PAPER && computerStrength == Strengths.ROCK
                    || userStrength == Strengths.SCISSORS && computerStrength == Strengths.PAPER -> {

                userScore++
                "You win!"
            }
            else -> {
                computerScore++
                "You lose!"
            }
        }
        binding.tvResult.text = result
    }

    private fun getComputerStrength(): Strengths {
        return Strengths.entries.toTypedArray().random()
    }
}
package com.example.reactiontimetester

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.reactiontimetester.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isClicking: Boolean = false
    private var countClicks: Int = 0
    private var rounds = mutableListOf<Int>()

    companion object {
        const val SECONDS_TO_PLAY = 10000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnStartStop.setOnClickListener {
            binding.btnStartStop.isActivated = !binding.btnStartStop.isActivated

            if (binding.btnStartStop.isActivated) {
                binding.btnStartStop.text = "RESET"
                binding.root.setBackgroundColor(Color.RED)

                val secondsToWait = (1..10).random() * 1000L

                isClicking = binding.root.postDelayed({
                    if (binding.btnStartStop.isActivated) {
                        binding.root.setBackgroundColor(Color.GREEN)
                        isClicking = true
                    }

                }, secondsToWait)

                binding.root.postDelayed({
                    if (binding.btnStartStop.isActivated) {
                        binding.root.setBackgroundColor(Color.RED)
                        isClicking = false

                        binding.tvScore.text = String.format("Clicks: %s", countClicks.toString())
                        binding.tvTime.text =
                            String.format("Seconds: %s", (SECONDS_TO_PLAY / 1000).toString())

                        rounds.add(countClicks)
                    }
                }, secondsToWait + SECONDS_TO_PLAY)

            } else {
                binding.btnStartStop.text = "START"
                binding.root.setBackgroundColor(Color.WHITE)

                clearText()

                isClicking = false
                countClicks = 0
            }
        }

        binding.btnClick.setOnClickListener {
            if (isClicking) {
                countClicks++
            }
        }

        binding.btnScoreboard.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("ROUNDS", ArrayList(rounds))
            }

            startActivity(intent)
        }

    }

    fun clearText() {
        binding.tvTime.text = ""
        binding.tvScore.text = ""

    }

}

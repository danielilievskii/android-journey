package com.example.rockpaperscissors

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rockpaperscissors.databinding.ActivityMainBinding
import com.example.rockpaperscissors.databinding.ActivitySecondBinding
import com.example.rockpaperscissors.ui.theme.RockPaperScissorsTheme

class SecondActivty : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userScore = intent.getIntExtra("USER_SCORE", 0)
        val computerScore = intent.getIntExtra("COMPUTER_SCORE", 0)
        val resultMsg = intent.getStringExtra("RESULT_MSG")

        binding.tvComputerScore.text = computerScore.toString()
        binding.tvUserScore.text = userScore.toString()

        binding.btnPlayAgain.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java).apply {
//                putExtra("USER_SCORE", userScore)
//                putExtra("COMPUTER_SCORE", computerScore)
//            }
//            startActivity(intent)
            finish()
        }
    }

}


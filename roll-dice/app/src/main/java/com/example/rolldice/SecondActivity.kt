package com.example.rolldice

import android.os.Bundle
import android.widget.ArrayAdapter
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
import com.example.rolldice.databinding.ActivitySecondBinding
import com.example.rolldice.ui.theme.RollDiceTheme

class SecondActivity : ComponentActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rounds = intent.getIntegerArrayListExtra("ROUNDS") ?: arrayListOf()

        val data = rounds.mapIndexed { index, value ->
            "Round ${index + 1}: $value - ${if (value > 7) "WIN" else "LOSE"}"
        }

        val adapter = ArrayAdapter(
            this,
            R.layout.list_item_view,
            R.id.textViewItem,
            data
        )

        binding.listView.adapter = adapter

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}


package mk.ukim.finki.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import mk.ukim.finki.quizapp.databinding.ActivityMainBinding
import mk.ukim.finki.quizapp.fragment.StartFragment
import mk.ukim.finki.quizapp.ui.theme.QuizAppTheme

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    if(savedInstanceState == null) {
      replaceFragment(StartFragment())
    }
  }

  fun replaceFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
      .replace(R.id.fragment_container, fragment)
      .addToBackStack(null)
      .commit()
  }
}


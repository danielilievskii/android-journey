package mk.ukim.finki.randomusergenerator

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
import androidx.fragment.app.commit
import mk.ukim.finki.randomusergenerator.fragment.GenerateFragment
import mk.ukim.finki.randomusergenerator.ui.theme.RandomUserGeneratorTheme

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContentView(R.layout.activity_main)

    if (savedInstanceState == null) {
      supportFragmentManager.commit {
        add(R.id.fragment_container_view, GenerateFragment())
        setReorderingAllowed(true)
      }
    }
  }
}


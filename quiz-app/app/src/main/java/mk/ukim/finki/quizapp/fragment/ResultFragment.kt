package mk.ukim.finki.quizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import mk.ukim.finki.quizapp.R
import mk.ukim.finki.quizapp.databinding.FragmentQuestionBinding
import mk.ukim.finki.quizapp.databinding.FragmentResultBinding
import mk.ukim.finki.quizapp.databinding.FragmentStartBinding
import mk.ukim.finki.quizapp.viewmodel.QuizViewModel
import mk.ukim.finki.quizapp.viewmodel.QuizViewModelFactory
import kotlin.getValue

class ResultFragment : Fragment() {

  private var _binding: FragmentResultBinding? = null
  private val binding get() = _binding!!

  private val quizViewModel: QuizViewModel by activityViewModels {
    QuizViewModelFactory(requireContext())
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentResultBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val score = quizViewModel.results.values.count { it }
    val totalQuestions = quizViewModel.questions.value?.size
    binding.tvScore.text = "Score: $score / $totalQuestions"

    binding.btnRestart.setOnClickListener {
      quizViewModel.loadQuestions()

      binding.btnRestart.setOnClickListener {

        val fragment = QuestionFragment().apply {
          arguments = Bundle().apply {
            putInt("questionIndex", 0)
          }
        }

        parentFragmentManager
          .beginTransaction()
          .replace(R.id.fragment_container, fragment)
          .addToBackStack(null)
          .commit()
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
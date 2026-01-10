package mk.ukim.finki.quizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.quizapp.R
import mk.ukim.finki.quizapp.databinding.FragmentStartBinding
import mk.ukim.finki.quizapp.viewmodel.QuizViewModel
import mk.ukim.finki.quizapp.viewmodel.QuizViewModelFactory

class StartFragment : Fragment() {

  private var _binding: FragmentStartBinding? = null
  private val binding get() = _binding!!
  private val quizViewModel: QuizViewModel by activityViewModels {
    QuizViewModelFactory(requireContext())
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentStartBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    quizViewModel.loadQuestions()

    binding.btnStart.setOnClickListener {
      val questionIndex = 0

      val fragment = QuestionFragment().apply {
        arguments = Bundle().apply {
          putInt("questionIndex", questionIndex)
        }
      }

      parentFragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .addToBackStack(null)
        .commit()
    }

  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}
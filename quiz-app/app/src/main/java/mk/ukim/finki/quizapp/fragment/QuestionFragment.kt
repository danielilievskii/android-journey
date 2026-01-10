package mk.ukim.finki.quizapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import mk.ukim.finki.quizapp.R
import mk.ukim.finki.quizapp.databinding.FragmentQuestionBinding
import mk.ukim.finki.quizapp.databinding.FragmentStartBinding
import mk.ukim.finki.quizapp.viewmodel.QuizViewModel
import mk.ukim.finki.quizapp.viewmodel.QuizViewModelFactory
import kotlin.getValue

class QuestionFragment : Fragment() {

  private var _binding: FragmentQuestionBinding? = null
  private val binding get() = _binding!!

  private val quizViewModel: QuizViewModel by activityViewModels {
    QuizViewModelFactory(requireContext())
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    _binding = FragmentQuestionBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    var index = arguments?.getInt("questionIndex") ?: 0

    if(index == 9) {
      binding.btnNext.text = "Finish"
    }

    val question = quizViewModel.questions.value?.get(index)

    val allAnswers = mutableListOf<String>().apply {
      add(question?.correct_answer ?: "")
      addAll(question?.incorrect_answers ?: emptyList())
    }.shuffled()

    binding.questionTv.text = question?.question
    binding.rb1.text = allAnswers[0]
    binding.rb2.text = allAnswers[1]
    binding.rb3.text = allAnswers[2]
    binding.rb4.text = allAnswers[3]

    binding.questionNumTv.text = index.toString()

    binding.btnNext.setOnClickListener {

      val selectedId = binding.rbGroup.checkedRadioButtonId
      val selectedAnswer = view.findViewById<RadioButton>(selectedId)?.text
      val isCorrect = selectedAnswer == question?.correct_answer
      quizViewModel.registerAnswer(index, isCorrect)

      val fragment = if (index < 9) {
        QuestionFragment().apply {
          arguments = Bundle().apply {
            putInt("questionIndex", ++index)
          }
        }
      } else {
        ResultFragment()
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
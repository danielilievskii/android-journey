package mk.ukim.finki.quizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mk.ukim.finki.quizapp.data.remote.dto.QuestionDto
import mk.ukim.finki.quizapp.data.repository.QuizRepository

class QuizViewModel(
  private val quizRepository: QuizRepository
) : ViewModel() {
  private val _questions = MutableLiveData<List<QuestionDto>>()
  val questions : LiveData<List<QuestionDto>> get() =  _questions

  private val _results = mutableMapOf<Int, Boolean>()
  val results: Map<Int, Boolean> get() = _results

  fun loadQuestions() {
    viewModelScope.launch {
      try {
        val questions = quizRepository.listQuestions()
        _questions.postValue(questions)
      } catch (e: Exception) {
        e.printStackTrace()
        _questions.postValue(emptyList())
      }
    }
  }

  fun registerAnswer(index: Int, isCorrect: Boolean) {
    _results[index] = isCorrect
  }

}
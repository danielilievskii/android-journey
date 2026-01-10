package mk.ukim.finki.quizapp.data.repository

import mk.ukim.finki.quizapp.data.remote.RemoteQuizDataSource
import mk.ukim.finki.quizapp.data.remote.dto.QuestionDto
import mk.ukim.finki.quizapp.utils.NetworkConnectivity

class QuizRepository(
  private val remoteQuizDataSource: RemoteQuizDataSource,
  private val networkConnectivity: NetworkConnectivity
) {

  suspend fun listQuestions() : List<QuestionDto> {

    return if(networkConnectivity.isNetworkAvailable) {
      remoteQuizDataSource.getQuestions()
    } else emptyList()
  }
}
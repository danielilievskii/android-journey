package mk.ukim.finki.quizapp.data.remote

import mk.ukim.finki.quizapp.data.remote.api.QuizApi
import mk.ukim.finki.quizapp.data.remote.dto.QuestionDto

class RemoteQuizDataSource(
  private val quizApi: QuizApi
) : IRemoteQuizDataSource {

  override suspend fun getQuestions(): List<QuestionDto> {
    val response = quizApi.getQuestions(10, "easy", "multiple")

    if(response.isSuccessful) {
      return response.body()?.results ?: emptyList()
    }

    throw Exception("Error fetching questions")
  }
}
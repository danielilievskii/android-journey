package mk.ukim.finki.quizapp.data.remote

import mk.ukim.finki.quizapp.data.remote.dto.QuestionDto

interface IRemoteQuizDataSource {
  suspend fun getQuestions() : List<QuestionDto>
}
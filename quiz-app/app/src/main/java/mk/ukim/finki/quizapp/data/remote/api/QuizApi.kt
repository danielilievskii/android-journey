package mk.ukim.finki.quizapp.data.remote.api

import mk.ukim.finki.quizapp.data.remote.dto.QuestionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {

  @GET("api.php")
  suspend fun getQuestions(
    @Query("amount") amount: Int,
    @Query("difficulty") difficulty: String,
    @Query("type") type: String
  ) : Response<QuestionsResponse>
}
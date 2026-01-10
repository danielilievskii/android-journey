package mk.ukim.finki.quizapp.data.remote.api

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class QuizApiProvider {

  companion object {
    @Volatile
    private var INSTANCE: QuizApi? = null

    @JvmStatic
    fun getQuizApi(): QuizApi {

      return INSTANCE ?: synchronized(this) {
        val instance = createMovieDbApi()
        INSTANCE = instance
        instance
      }
    }

    private fun createMovieDbApi(): QuizApi {

      val gson = GsonBuilder()
        .setLenient()
        .create()

      val gsonConverterFactory = GsonConverterFactory.create(gson)

      val retrofit = Retrofit.Builder()
        .baseUrl("https://opentdb.com/")
        .addConverterFactory(gsonConverterFactory)
        .build()

      return retrofit.create(QuizApi::class.java)
    }
  }


}
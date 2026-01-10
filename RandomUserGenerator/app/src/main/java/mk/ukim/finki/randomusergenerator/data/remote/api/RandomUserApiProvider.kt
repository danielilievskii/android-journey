package mk.ukim.finki.randomusergenerator.data.remote.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RandomUserApiProvider {

  companion object {
    @Volatile
    private var INSTANCE: RandomUserApi? = null

    @JvmStatic
    fun getRandomUserApi(): RandomUserApi {

      return INSTANCE ?: synchronized(this) {
        val instance = createMovieDbApi()
        INSTANCE = instance
        instance
      }
    }

    private fun createMovieDbApi(): RandomUserApi {

      val okhttpClient = OkHttpClient.Builder()
        .build()

      val gson = GsonBuilder()
        .setLenient()
        .create()

      val gsonConverterFactory = GsonConverterFactory.create(gson)

      val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .client(okhttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

      return retrofit.create(RandomUserApi::class.java)
    }
  }
}
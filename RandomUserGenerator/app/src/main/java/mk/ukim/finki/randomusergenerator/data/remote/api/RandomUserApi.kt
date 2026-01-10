package mk.ukim.finki.randomusergenerator.data.remote.api

import mk.ukim.finki.randomusergenerator.data.remote.dto.RandomUserResponse
import retrofit2.Response
import retrofit2.http.GET

interface RandomUserApi {

  @GET("api/")
  suspend fun generateUser(): Response<RandomUserResponse>
}
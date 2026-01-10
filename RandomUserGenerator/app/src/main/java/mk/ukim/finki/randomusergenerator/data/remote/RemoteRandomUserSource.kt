package mk.ukim.finki.randomusergenerator.data.remote

import mk.ukim.finki.randomusergenerator.data.remote.api.RandomUserApi
import mk.ukim.finki.randomusergenerator.data.remote.dto.RandomUserDto

class RemoteRandomUserSource (
  private val randomUserApi: RandomUserApi
): IRemoteRandomUserSource {
  override suspend fun generateUser(): RandomUserDto {

    val response = randomUserApi.generateUser()
    val results = response.body()?.results
    if(response.isSuccessful && results != null) {
      return results[0]
    }
    throw Exception("Error searching movies")
  }
}
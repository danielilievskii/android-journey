package mk.ukim.finki.randomusergenerator.data.repository

import com.example.moviesapp.utils.NetworkConnectivity
import mk.ukim.finki.randomusergenerator.data.remote.RemoteRandomUserSource
import mk.ukim.finki.randomusergenerator.data.remote.dto.PictureDto
import mk.ukim.finki.randomusergenerator.data.remote.dto.RandomUserDto

class RandomUserRepository(
  private val remoteRandomUserSource: RemoteRandomUserSource,
  private val networkConnectivity: NetworkConnectivity
) {

  suspend fun generateUser(): RandomUserDto {
    if (networkConnectivity.isNetworkAvailable) {
      return remoteRandomUserSource.generateUser()
    }
    return RandomUserDto(email="default", picture = PictureDto(large="url"))
  }
}
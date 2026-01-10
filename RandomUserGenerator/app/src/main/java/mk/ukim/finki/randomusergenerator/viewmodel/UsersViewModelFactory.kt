package mk.ukim.finki.randomusergenerator.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapp.utils.NetworkConnectivity
import mk.ukim.finki.randomusergenerator.data.remote.RemoteRandomUserSource
import mk.ukim.finki.randomusergenerator.data.remote.api.RandomUserApiProvider
import mk.ukim.finki.randomusergenerator.data.repository.RandomUserRepository

class UsersViewModelFactory(private val context: Context): ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {

    return modelClass.getConstructor(RandomUserRepository::class.java)
      .newInstance(RandomUserRepository(
        RemoteRandomUserSource(RandomUserApiProvider.getRandomUserApi()),
        NetworkConnectivity(context)
      ))
  }
}
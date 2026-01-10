package mk.ukim.finki.randomusergenerator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mk.ukim.finki.randomusergenerator.data.remote.dto.PictureDto
import mk.ukim.finki.randomusergenerator.data.remote.dto.RandomUserDto
import mk.ukim.finki.randomusergenerator.data.repository.RandomUserRepository

class UsersViewModel (
  private val randomUserRepository: RandomUserRepository
): ViewModel() {

  private val _users = MutableLiveData<List<RandomUserDto>>()
  val users: LiveData<List<RandomUserDto>> = _users

  private val _latestGeneratedUser = MutableLiveData<RandomUserDto>()
  val latestGeneratedUser: LiveData<RandomUserDto> = _latestGeneratedUser


  fun generateUser() {
    viewModelScope.launch {

      val user = randomUserRepository.generateUser()
      _latestGeneratedUser.postValue(user)

      val currentList = _users.value ?: emptyList()
      val updatedList = currentList + user

      _users.postValue(updatedList)
    }
  }
}
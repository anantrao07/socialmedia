package life.league.challenge.kotlin.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.database.UsersTable
import life.league.challenge.kotlin.repository.UserRepository

class UserViewModel( application: Application) : ViewModel() {
    private val userRepository = UserRepository(application)
    val userData: MutableLiveData<UsersTable> = MutableLiveData()

    class Factory constructor(private val application: Application): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserViewModel(application) as T
        }
    }

    fun fetchUser(userId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userData.postValue(userRepository.getUserInfo(userId))
        }
    }
}
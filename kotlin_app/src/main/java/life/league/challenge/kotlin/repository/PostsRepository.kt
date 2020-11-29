package life.league.challenge.kotlin.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import life.league.challenge.kotlin.api.Failure
import life.league.challenge.kotlin.api.Service
import life.league.challenge.kotlin.api.Success
import life.league.challenge.kotlin.database.LeagueDatabase
import life.league.challenge.kotlin.database.LeagueDatabase.Companion.getInstance
import life.league.challenge.kotlin.database.PostTable
import life.league.challenge.kotlin.database.UsersTable
import life.league.challenge.kotlin.model.AuthApi
import life.league.challenge.kotlin.model.UserPostsItem

class PostsRepository(application: Application) {

    private val database: LeagueDatabase? = getInstance(application)
    private val _userPostList = MutableLiveData<List<UserPostsItem.UserPost>>()
    val userPostList: LiveData<List<UserPostsItem.UserPost>>
    get() { return _userPostList }

    private val _responseError = MutableLiveData<Error>()
    val responseError:LiveData<Error>
    get() {return _responseError}

    suspend fun fetchUsers() {
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            val deferedUsers = async {
                Service.login("test@gmail.com", "pass") { result ->
                    when (result) {
                        is Success -> {
                            Service.authApi = AuthApi(result.response?.apiKey)
                            Service.authApi?.apiKey?.let {
                                Service.users(it) { result ->
                                    when (result) {
                                        is Success -> {
                                            result.response?.let { userList ->
                                                saveUsers(database, userList)
                                            }
                                        }
                                        is Failure -> {
                                            _responseError.postValue(Error())
                                            Log.e("PostsRepository", result.errorResponse)
                                        }
                                    }
                                }
                            }
                        }
                        is Failure -> Log.e("PostsRepository", result.errorResponse)
                    }
                }
            }
            deferedUsers.await()
            val deferedPost = async {
                fetchPosts(Service.authApi?.apiKey)
            _userPostList.postValue(getUsersPost())
            }
            deferedPost.await()

        }
    }

    private fun saveUsers(database: LeagueDatabase?, userEntityList: List<UsersTable>) {
            database?.userDao()?.insertUsers(userEntityList)
    }

     private fun fetchPosts(apiKey: String?) {
        Service.posts(apiKey) { result ->
            when (result) {
                is Success -> {
                    result.response?.let { postList ->
                        savePosts(database, postList)
                    }
                }
                is Failure -> {
                    _responseError.postValue(Error())
                }
            }
         }
    }

    private fun savePosts(database: LeagueDatabase?, postEntityList: List<PostTable>) {
            database?.postDao()?.insertPosts(postEntityList)

    }


    fun getUsersPost(): List<UserPostsItem.UserPost>? {
        return database?.postDao()?.getUsersPost()
    }
}

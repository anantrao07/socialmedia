package life.league.challenge.kotlin.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    var userPostList: MutableLiveData<List<UserPostsItem.UserPost>> = MutableLiveData()
    fun fetchUsers() {
        CoroutineScope(Dispatchers.IO).launch {
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
                                            fetchPosts(it)
                                            userPostList.postValue(getUsersPost())
                                    }
                                    is Failure -> Log.e("PostsRepository", result.errorResponse)
                                }
                            }
                        }
                    }
                    is Failure -> Log.e("PostsRepository", result.errorResponse)
                }
            }
        }
    }

    private fun saveUsers(database: LeagueDatabase?, userEntityList: List<UsersTable>) {
        CoroutineScope(Dispatchers.IO).launch {
            database?.userDao()?.insertUsers(userEntityList)
        }
    }

     private fun fetchPosts(apiKey: String?) {
         CoroutineScope(Dispatchers.IO).launch {
        Service.posts(apiKey) { result ->
            when (result) {
                is Success -> {
                    result.response?.let { postList ->
                        savePosts(database, postList)
                    }
                }
                is Failure -> {
                }
            }
         }
        }
    }

    private fun savePosts(database: LeagueDatabase?, postEntityList: List<PostTable>) {
        CoroutineScope(Dispatchers.IO).launch {
            database?.postDao()?.insertPosts(postEntityList)
        }
    }


    fun getUsersPost(): List<UserPostsItem.UserPost>? {
        return database?.postDao()?.getUsersPost()
    }
}

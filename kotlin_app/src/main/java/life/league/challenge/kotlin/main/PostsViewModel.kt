package life.league.challenge.kotlin.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.model.UserPostsItem
import life.league.challenge.kotlin.repository.PostsRepository

class PostsViewModel(application: Application) : ViewModel() {
    private val postRepository: PostsRepository = PostsRepository(application)
    var postList: MutableLiveData<List<UserPostsItem.UserPost>>? = postRepository.userPostList

    companion object {
        private const val TAG = "PostsViewModel"
    }

    class Factory constructor(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PostsViewModel(application) as T
        }
    }

    fun fetchPosts() {
        CoroutineScope(Dispatchers.IO).launch {
            postRepository.fetchUsers()
        }
    }
}
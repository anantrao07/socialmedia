package life.league.challenge.kotlin.api

import android.util.Base64
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.database.PostTable
import life.league.challenge.kotlin.database.UsersTable
import life.league.challenge.kotlin.model.Account
import life.league.challenge.kotlin.model.AuthApi
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object Service {

    var authApi: AuthApi? = null
    private const val HOST = "https://engineering.league.dev/challenge/api/"
    private const val TAG = "Service"
    private val api: Api by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofit.create<Api>(Api::class.java)
    }

    private val coroutine = CoroutineScope(Dispatchers.IO)

    fun login(username: String, password: String, callback: (result: Outcome<Account>) -> Unit) {

        val credentials = "$username:$password"
        val auth = "Basic " + Base64.encodeToString(credentials.toByteArray(),
                Base64.NO_WRAP)
        coroutine.launch{
            try {
                val response: Response<Account> = api.login(auth).execute()
                    if (response.isSuccessful) {
                        callback.invoke(Success(response.body()))
                    } else {
                        callback.invoke(Failure(response.message()))
                    }
            } catch (e: IOException) {
                Log.e(TAG, "Login failed", e)
                callback.invoke(Failure(e.message ?: ""))
            }
        }
    }

    fun posts(apiKey: String?, callback: (result: Outcome<List<PostTable>>) -> Unit) {
        coroutine.launch{
            try {
                val response = api.userPosts(apiKey).execute()
                    if (response.isSuccessful) {
                        callback.invoke(Success(response.body()))
                    } else {
                        callback.invoke(Failure(response.message()))
                    }
            } catch (e: IOException) {
                Log.e(TAG, "fetch Posts failed", e)
                callback.invoke(Failure(e.message ?: ""))
            }
        }
    }

    fun users(apiKey: String?, callback: (result: Outcome<List<UsersTable>>) -> Unit) {
        coroutine.launch {
            val response = api.users(apiKey).execute()
            if (response.isSuccessful) {
                callback.invoke(Success(response.body()))
            } else{
                callback.invoke(Failure(response.message()))
            }
        }
    }
}



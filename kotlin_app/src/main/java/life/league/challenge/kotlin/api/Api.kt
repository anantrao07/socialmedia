package life.league.challenge.kotlin.api

import life.league.challenge.kotlin.constants.Constants.HEADER_AUTH_KEY
import life.league.challenge.kotlin.constants.Constants.HEADER_TOKEN_KEY
import life.league.challenge.kotlin.database.PostTable
import life.league.challenge.kotlin.database.UsersTable
import life.league.challenge.kotlin.model.Account
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface Api {


    @GET("login")
    fun login(@Header(HEADER_AUTH_KEY) credentials: String?): Call<Account>

    @GET("users")
    fun users(@Header(HEADER_TOKEN_KEY) apiKey: String?): Call<List<UsersTable>>

    fun users()
    @GET("posts")
    fun userPosts(@Header(HEADER_TOKEN_KEY) apiKey: String?): Call<List<PostTable>>

}

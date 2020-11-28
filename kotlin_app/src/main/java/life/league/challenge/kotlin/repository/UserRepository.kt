package life.league.challenge.kotlin.repository

import android.app.Application
import life.league.challenge.kotlin.database.LeagueDatabase
import life.league.challenge.kotlin.database.UsersTable

class UserRepository (application: Application) {
    private val database: LeagueDatabase? = LeagueDatabase.getInstance(application)

    fun getUserInfo(userId: Int): UsersTable? {
        return database?.userDao()?.getUserById(userId)
    }
}
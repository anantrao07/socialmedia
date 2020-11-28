package life.league.challenge.kotlin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import life.league.challenge.kotlin.databaseconstants.UsersConstants.GET_USER_BY_ID

@Dao
interface UserDao {
    @Insert(onConflict = REPLACE)
    fun insertUsers(usersList: List<UsersTable>?)

    @Query(GET_USER_BY_ID)
    fun getUserById(userId: Int): UsersTable
}
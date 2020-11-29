package life.league.challenge.kotlin

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.database.LeagueDatabase
import life.league.challenge.kotlin.database.UserDao
import life.league.challenge.kotlin.database.UsersTable
import life.league.challenge.kotlin.model.Address
import life.league.challenge.kotlin.model.Company
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.coroutines.CoroutineContext

@RunWith(AndroidJUnit4::class)
class DbTest {
    private lateinit var userDao: UserDao
    private lateinit var db: LeagueDatabase

    @Before
    fun createDB(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context,
                LeagueDatabase::class.java
                        ).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun close(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadList(){
        val user = listOf((UsersTable(100, "image/url", "name", Address(), "hfx@ns.com", "9099099009", "gmail.com", Company())))
        CoroutineScope(Dispatchers.IO).launch{
        userDao.insertUsers(user)
        val userObject = userDao.getUserById(100)
        assertTrue(userObject.userId== user[0].userId)
        }
    }
}

package life.league.challenge.kotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import life.league.challenge.kotlin.databaseconstants.DBConstants.NBA_DATABASE_NAME
import life.league.challenge.kotlin.database.converter.*

@Database(entities = [UsersTable::class, PostTable::class], version = 1, exportSchema = false)
@TypeConverters(UsersTypeConverter::class, AddressTypeConverter::class,
        CompanyTypeConverter::class, GeoTypeConverter::class,
        PostTypeConverter::class)
abstract class LeagueDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao

    companion object {
        private val LOCK = Any()
        @Volatile private var instance: LeagueDatabase? = null
        fun getInstance(context: Context): LeagueDatabase? {
            if (instance == null) {
                synchronized(LOCK) {
                    instance = Room.databaseBuilder(
                            context.applicationContext
                            , LeagueDatabase::class.java,
                            NBA_DATABASE_NAME)
                            .build()
                }
            }
            return instance
        }
    }
}
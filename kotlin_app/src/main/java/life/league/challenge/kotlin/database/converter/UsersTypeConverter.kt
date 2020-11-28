package life.league.challenge.kotlin.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import life.league.challenge.kotlin.database.UsersTable
import life.league.challenge.kotlin.model.Address
import life.league.challenge.kotlin.model.Company
import life.league.challenge.kotlin.model.Geo

object UsersTypeConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToList(data: String?): List<UsersTable>{
        if (data.isNullOrBlank()){
            return emptyList()
        }
        val listType = object : TypeToken<List<UsersTable>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun listToString(someObject: List<UsersTable>): String {
        return gson.toJson(someObject)
    }
}

object AddressTypeConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToObject(data: String): Address {
        return gson.fromJson(data, Address::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun objectToString(someObjects: Address): String {
        return gson.toJson(someObjects)
    }
}

object CompanyTypeConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToObject(data: String): Company {
        return gson.fromJson(data, Company::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun objectToString(someObjects: Company): String {
        return gson.toJson(someObjects)
    }
}

object GeoTypeConverter {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToObject(data: String): Geo {
        return gson.fromJson(data, Geo::class.java)
    }

    @TypeConverter
    @JvmStatic
    fun objectToString(someObjects: Geo): String {
        return gson.toJson(someObjects)
    }
}
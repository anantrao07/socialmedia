package life.league.challenge.kotlin.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import life.league.challenge.kotlin.database.PostTable

object PostTypeConverter {

    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToList(data: String?): List<PostTable>{
        if (data.isNullOrBlank()){
            return emptyList()
        }
        val listType = object : TypeToken<List<PostTable>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun listToString(someObject: List<PostTable>): String {
        return gson.toJson(someObject)
    }
}
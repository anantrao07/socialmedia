package life.league.challenge.kotlin.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize
import life.league.challenge.kotlin.databaseconstants.DBConstants.ID_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.POST_DESCRIPTION_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.POST_ID_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.POST_TABLE_NAME
import life.league.challenge.kotlin.databaseconstants.DBConstants.POST_TITLE_COLUMN
import org.jetbrains.annotations.NotNull

@Entity(tableName = POST_TABLE_NAME)
@Parcelize
class PostTable() : Parcelable {

    @PrimaryKey
    @Expose
    @NotNull
    @ColumnInfo(name = POST_ID_COLUMN)
    var postId: Int = 0

    @Expose
    @NotNull
    @ColumnInfo(name = ID_COLUMN)
    var userId: Int = 0

    @Expose
    @NotNull
    @ColumnInfo(name = POST_TITLE_COLUMN)
    var title: String = ""

    @Expose
    @NotNull
    @ColumnInfo(name = POST_DESCRIPTION_COLUMN)
    var description: String = ""

    constructor(userId: Int, postId: Int, title: String, description: String) : this() {
        this.userId = userId
        this.postId = postId
        this.title = title
        this.description = description
    }
}
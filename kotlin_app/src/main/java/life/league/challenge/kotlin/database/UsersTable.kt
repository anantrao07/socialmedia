package life.league.challenge.kotlin.database

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_ADDRESS_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_AVATAR_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_COMPANY_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_EMAIL_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_ID_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_NAME_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_PHONE_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_TABLE_NAME
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_WEBSITE_COLUMN
import life.league.challenge.kotlin.database.converter.AddressTypeConverter
import life.league.challenge.kotlin.database.converter.CompanyTypeConverter
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_USER_NAME_COLUMN
import life.league.challenge.kotlin.model.Address
import life.league.challenge.kotlin.model.Company
import org.jetbrains.annotations.NotNull

@Entity(tableName = USER_TABLE_NAME)
@Parcelize
class UsersTable(): Parcelable {
    @PrimaryKey
    @Expose
    @NotNull
    @SerializedName("id")
    @ColumnInfo(name = USER_ID_COLUMN)
    var userId: Int = 0

    @Expose
    @NotNull
    @ColumnInfo(name = USER_NAME_COLUMN)
    var name: String = ""

    @Expose
    @NotNull
    @ColumnInfo(name = USER_USER_NAME_COLUMN)
    var userName: String = ""

    @Expose
    @NotNull
    @ColumnInfo(name = USER_PHONE_COLUMN)
    var phone: String = ""

    @Expose
    @NotNull
    @ColumnInfo(name = USER_AVATAR_COLUMN)
    var avatar: String = ""

    @Expose
    @NotNull
    @ColumnInfo(name = USER_EMAIL_COLUMN)
    var email: String = ""

    @Expose
    @NotNull
    @ColumnInfo(name = USER_WEBSITE_COLUMN)
    var website: String = ""

    @Expose
    @NotNull
    @TypeConverters(AddressTypeConverter::class)
    @ColumnInfo(name = USER_ADDRESS_COLUMN)
    var address: Address = Address()

    @Expose
    @NotNull
    @TypeConverters(CompanyTypeConverter::class)
    @ColumnInfo(name = USER_COMPANY_COLUMN)
    var company: Company = Company()

    constructor(id: Int, avatar: String, name: String, address: Address, email: String, phone: String, website: String, company: Company ) : this() {
        this.userId = id
        this.avatar = avatar
        this.name = name
        this.address = address
        this.email = email
        this.phone = phone
        this.website = website
        this.company = company
    }
}
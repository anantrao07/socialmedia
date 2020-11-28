package life.league.challenge.kotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
        val address: Address,
        val avatar: String,
        val company: Company,
        val email: String,
        val id: Int,
        val name: String,
        val phone: String,
        val username: String,
        val website: String
): Parcelable

@Parcelize
data class Address(
        var city: String = "",
        var geo: Geo = Geo(),
        var street: String = "",
        var suite: String = "",
        var zipcode: String = ""
): Parcelable

@Parcelize
data class Company(
        var bs: String = "",
        var catchPhrase: String = "",
        var name: String = ""
): Parcelable

@Parcelize
data class Geo(
        var lat: String = "",
        var lng: String = ""
):Parcelable
package life.league.challenge.kotlin.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

sealed class PostItem: DataItem {

    @Parcelize
    data class Post(
            val body: String,
            @SerializedName("id")
            val postId: Int,
            val title: String,
            val userId: Int
    ) : PostItem(), Parcelable

    override fun getId(): Int {
        TODO("Not yet implemented")
    }
}
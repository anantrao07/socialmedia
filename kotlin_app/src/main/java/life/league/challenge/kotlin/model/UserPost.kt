package life.league.challenge.kotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class UserPostsItem : DataItem {
    @Parcelize
    data class UserPost(
            var avatar: String? = "",
            var name: String = "",
            var postId: Int = 0,
            var description: String? = "",
            var title: String? = "",
            var userId: Int = 0
    ): UserPostsItem(), Parcelable

    override fun getId(): Int {
        TODO("Not yet implemented")
    }
}

package life.league.challenge.kotlin.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import life.league.challenge.kotlin.databaseconstants.PostsConstants
import life.league.challenge.kotlin.model.UserPostsItem.UserPost

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(postsList: List<PostTable>?)

    @Query(PostsConstants.GET_ALL_USERS_POSTS)
    fun getUsersPost(): List<UserPost>
}
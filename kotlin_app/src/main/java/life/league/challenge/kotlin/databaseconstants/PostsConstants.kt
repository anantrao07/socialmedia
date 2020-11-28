package life.league.challenge.kotlin.databaseconstants

import life.league.challenge.kotlin.databaseconstants.DBConstants.POST_DESCRIPTION_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.POST_ID_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.POST_TABLE_NAME
import life.league.challenge.kotlin.databaseconstants.DBConstants.POST_TITLE_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_AVATAR_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_ID_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_NAME_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_TABLE_NAME

object PostsConstants {
    const val GET_ALL_USERS_POSTS = "select $USER_TABLE_NAME.$USER_AVATAR_COLUMN, $USER_TABLE_NAME.${USER_NAME_COLUMN}, $POST_TABLE_NAME.${POST_ID_COLUMN}, $POST_TABLE_NAME.${POST_DESCRIPTION_COLUMN} , $POST_TABLE_NAME.$POST_TITLE_COLUMN, $POST_TABLE_NAME.$USER_ID_COLUMN from $USER_TABLE_NAME  inner join  $POST_TABLE_NAME on  $USER_TABLE_NAME.${USER_ID_COLUMN} == ${POST_TABLE_NAME}.${USER_ID_COLUMN}"
}
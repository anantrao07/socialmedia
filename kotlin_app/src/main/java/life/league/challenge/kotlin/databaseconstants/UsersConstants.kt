package life.league.challenge.kotlin.databaseconstants

import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_ID_COLUMN
import life.league.challenge.kotlin.databaseconstants.DBConstants.USER_TABLE_NAME

object UsersConstants {

    const val GET_USER_BY_ID = "select * from $USER_TABLE_NAME where $USER_TABLE_NAME.$USER_ID_COLUMN = :$USER_ID_COLUMN "
}
package op.mobile.app.dev.chalbr1.travelling.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Result
 *
 * stores how the database for results stores the result table
 * @property countryName
 * @property score
 * @constructor Create empty Result
 */
@Entity(tableName = "result")
data class Result (
    @ColumnInfo(name = "countryName")
    var countryName: String,

    @ColumnInfo(name = "score")
    var score: Int
    ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}
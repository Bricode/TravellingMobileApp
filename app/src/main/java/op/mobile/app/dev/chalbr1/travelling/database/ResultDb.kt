package op.mobile.app.dev.chalbr1.travelling.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import op.mobile.app.dev.chalbr1.travelling.model.Result

/**
 * Result db
 *
 * The database for quiz results
 * @constructor Create empty Result db
 */
    @Database(entities = [Result::class], version = 1, exportSchema = true)
    abstract class ResultDb : RoomDatabase() {
        abstract fun resultDao(): IResultDao

        companion object {
            @Volatile
            private var INSTANCE: ResultDb? = null

            fun getDatabase(
                context: Context
            ): ResultDb {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ResultDb::class.java,
                        "result_database"
                    )
                        .build()
                    INSTANCE = instance
                    instance
                }
            }
        }
    }

package op.mobile.app.dev.chalbr1.travelling.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import op.mobile.app.dev.chalbr1.travelling.model.Result

/**
 * I result dao
 *
 * The I result database access object stores queries to the database and what funcs they are attached too
 * @constructor Create empty I result dao
 */
@Dao
interface IResultDao {
    /**gets all results from the database*/
    @Query("SELECT * FROM result")
    fun getAll(): Flow<List<Result>>

    /** function to insert a result */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(result : Result)
}
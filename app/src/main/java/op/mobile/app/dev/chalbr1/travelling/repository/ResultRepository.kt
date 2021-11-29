package op.mobile.app.dev.chalbr1.travelling.repository

import androidx.annotation.WorkerThread
import op.mobile.app.dev.chalbr1.travelling.database.IResultDao
import op.mobile.app.dev.chalbr1.travelling.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * Result repository
 *
 * contains the async methods to run the database access object
 * @property resultsDao
 * @constructor Create empty Result repository
 */
class ResultRepository(private val resultsDao: IResultDao){
    /**sets a value to a live list of results from the results database access object*/
    val allScores: Flow<List<Result>> = resultsDao.getAll()

    /**returns a live list of Results from the database access object */
    @WorkerThread
    suspend fun getAll(): Flow<List<Result>> {
        return resultsDao.getAll()
    }
    /**inserts the passed through result*/
    @WorkerThread
    suspend fun insertResultDetail(result: Result) {
        resultsDao.insert(result)
    }
}
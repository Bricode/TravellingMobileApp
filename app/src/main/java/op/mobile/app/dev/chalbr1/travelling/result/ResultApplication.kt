package op.mobile.app.dev.chalbr1.travelling.result

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import op.mobile.app.dev.chalbr1.travelling.database.ResultDb
import op.mobile.app.dev.chalbr1.travelling.repository.ResultRepository

/**
 * Result application
 *
 * @constructor Create empty Result application
 */
class ResultApplication() : Application() {
    /**
     * Database
     */
    private val database by lazy { ResultDb.getDatabase(this) }

    /**
     * Repository
     */
    val repository by lazy { ResultRepository(database.resultDao()) }
}
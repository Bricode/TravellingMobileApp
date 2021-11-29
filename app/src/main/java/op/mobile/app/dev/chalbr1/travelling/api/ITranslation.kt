package op.mobile.app.dev.chalbr1.travelling.api

import op.mobile.app.dev.chalbr1.travelling.model.Translation
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * I translation
 *
 * This is the translation interface
 * @constructor Create empty I translation
 */
interface ITranslation {
    /**
     * this function passes the required fields needed when calling a translation
     */
    @GET("translate?")
    suspend fun getResponse(
        @Query("key") key: String,
        @Query("text") text: String,
        @Query("lang") lang : String
    ): Translation
}
package op.mobile.app.dev.chalbr1.travelling.api

import op.mobile.app.dev.chalbr1.travelling.model.Country
import retrofit2.http.GET

/**
 * I country
 *
 * This is the Country interface
 * @constructor Create empty I country
 */
interface ICountry {
    /**
     * provides a fun when the Icountry is called
     */
    @GET("raw")
    suspend fun getResponse(): List<Country>
}
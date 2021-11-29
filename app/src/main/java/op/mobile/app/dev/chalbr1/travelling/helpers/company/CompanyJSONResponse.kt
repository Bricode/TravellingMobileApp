package op.mobile.app.dev.chalbr1.travelling.helpers.company

import com.google.android.gms.maps.model.LatLng
import op.mobile.app.dev.chalbr1.travelling.model.Company

/**
 * Company j s o n response
 *
 * the class that defines a company or in my case country's tourist attraction
 * @property name
 * @property city
 * @property location
 * @constructor Create empty Company j s o n response
 */
data class CompanyJSONResponse(val name: String, val city: String, val location: Location) {
    /**
     * location object is comprised of a latitude and a longitude
     */
    data class Location(
        val latitude: Double,
        val longitude: Double
    )
}

/**
 * function to convert a company's seperate values into a company
 */
fun CompanyJSONResponse.toCompany() = Company(
    name = name,
    city = city,
    latLng = LatLng(location.latitude, location.longitude)
)
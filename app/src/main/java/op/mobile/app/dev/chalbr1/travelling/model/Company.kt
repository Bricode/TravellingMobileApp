package op.mobile.app.dev.chalbr1.travelling.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

/**
 * Company
 *
 * Company model, stores what defines a company
 * @property latLng
 * @property name
 * @property city
 * @constructor Create empty Company
 */
data class Company (val latLng: LatLng, val name: String, val city: String): ClusterItem {
    override fun getPosition(): LatLng = latLng
    override fun getTitle(): String = name
    override fun getSnippet(): String = ""
}
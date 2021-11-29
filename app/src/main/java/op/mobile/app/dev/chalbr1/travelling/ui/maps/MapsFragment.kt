package op.mobile.app.dev.chalbr1.travelling.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.maps.android.clustering.ClusterManager
import op.mobile.app.dev.chalbr1.travelling.R
import op.mobile.app.dev.chalbr1.travelling.model.Company
import op.mobile.app.dev.chalbr1.travelling.helpers.company.CompanyJSONReader
import op.mobile.app.dev.chalbr1.travelling.helpers.company.CompanyMarkerCluster

/**
 * Maps fragment
 *
 * @constructor Create empty Maps fragment
 */
class MapsFragment : Fragment(), OnMapReadyCallback {
    /**
     * On create view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_maps, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        return view
    }

    /**
     * On map ready
     *
     * @param googleMap
     */
    override fun onMapReady(googleMap: GoogleMap) {
        val companies = CompanyJSONReader(requireContext()).read()
        val clusterManager: ClusterManager<Company> = ClusterManager(requireContext(), googleMap)
        val markerCluster = CompanyMarkerCluster(requireContext(), googleMap, clusterManager)
        clusterManager.renderer = markerCluster
        clusterManager.addItems(companies)
        clusterManager.cluster()
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(companies[0].position, 10f))
    }
}
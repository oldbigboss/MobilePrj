package `fun`.veres.mobileprj.utils

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import kotlin.math.abs


fun adjustBoundsForMaxZoomLevel(bounds: LatLngBounds): LatLngBounds {
    var boundsToReturn = bounds
    var sw = bounds.southwest
    var ne = bounds.northeast
    val deltaLat = abs(sw.latitude - ne.latitude)
    val deltaLon = abs(sw.longitude - ne.longitude)
    val zoomN = 0.005
    if (deltaLat < zoomN) {
        sw = LatLng(sw.latitude - (zoomN - deltaLat / 2), sw.longitude)
        ne = LatLng(ne.latitude + (zoomN - deltaLat / 2), ne.longitude)
        boundsToReturn = LatLngBounds(sw, ne)
    } else if (deltaLon < zoomN) {
        sw = LatLng(sw.latitude, sw.longitude - (zoomN - deltaLon / 2))
        ne = LatLng(ne.latitude, ne.longitude + (zoomN - deltaLon / 2))
        boundsToReturn = LatLngBounds(sw, ne)
    }
    return boundsToReturn
}
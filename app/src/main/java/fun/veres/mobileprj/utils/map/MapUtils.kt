package `fun`.veres.mobileprj.utils.map

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.SphericalUtil
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


class MapUtils {

    fun generateRouteByTwoPints(
        startCoordinate: LatLng,
        endCoordinate: LatLng,
    ): List<LatLng> {
        var count = 1000
        count += 1
        val d: Double =
            sqrt(
                (startCoordinate.latitude - endCoordinate.latitude) *
                        (startCoordinate.latitude - endCoordinate.latitude) +
                        (startCoordinate.longitude - endCoordinate.longitude) *
                        (startCoordinate.longitude - endCoordinate.longitude)
            ) / count
        val fi: Double = atan2(
            endCoordinate.longitude - startCoordinate.longitude,
            endCoordinate.latitude - startCoordinate.latitude
        )
        val points = mutableListOf<LatLng>()
        for (i in 0..count) points.add(
            LatLng(
                startCoordinate.latitude + i * d * cos(fi),
                startCoordinate.longitude + i * d * sin(fi)
            )
        )
        return points
    }

    fun setAnimation(myMap: GoogleMap, directionPoints: List<LatLng>, bitmap: Bitmap) {
        val marker = myMap.addMarker(
            MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(bitmap))
                .position(directionPoints[0])
                .flat(true)
        )
        animateMarker(marker, directionPoints)
    }

    private fun animateMarker(marker: Marker, directionPoint: List<LatLng?>) {
        val handler = Handler(Looper.getMainLooper())
        val start = SystemClock.uptimeMillis()
        val duration: Long = 300000
        val interpolator: Interpolator = LinearInterpolator()
        handler.post(object : Runnable {
            var i = 0
            override fun run() {
                val elapsed = SystemClock.uptimeMillis() - start
                val t: Float = interpolator.getInterpolation(elapsed.toFloat() / duration)
                if (i < directionPoint.size) {
                    marker.position = (directionPoint[i])!!
                    if (i > 0) {
                        marker.rotation = SphericalUtil.computeHeading(
                            directionPoint[i], directionPoint[i - 1]).toFloat()
                    }
                }
                i++
                if (t < 1.0) {
                    handler.postDelayed(this, 70)
                }
            }
        })
    }

}
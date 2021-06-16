package `fun`.veres.mobileprj.utils.databinding

import `fun`.veres.mobileprj.R
import `fun`.veres.mobileprj.ui.base.BaseRvAdapter
import `fun`.veres.mobileprj.ui.base.BaseViewModel
import `fun`.veres.mobileprj.uicomponents.AtMap
import `fun`.veres.mobileprj.utils.map.MapUtils
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.View
import android.widget.EditText
import androidx.annotation.Keep
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*


@Keep
@BindingAdapter("adapter", "viewmodel", "datasource", requireAll = false)
fun <T> setUpRecyclerView(
    rv: RecyclerView,
    adapter: BaseRvAdapter<T>,
    viewModel: BaseViewModel?,
    data: List<T>?
) {
    viewModel?.let { adapter.viewmodel = it }
    if (rv.adapter == null) rv.adapter = adapter
    data?.let { adapter.updateData(it) }
}

@Keep
@BindingAdapter("focusChangedListener")
fun setTextFocus(editText: EditText, listener: View.OnFocusChangeListener) {
    editText.onFocusChangeListener = listener
}

@Keep
@BindingAdapter("isVisible")
fun setViewVisible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("markers")
fun addMarkers(map: AtMap, markers: List<LatLng>?) {
    map.getMapAsync { googleMap ->
        if (markers != null) {
            val height: Int = map.context.resources.displayMetrics.heightPixels
            val padding = (height * 0.1).toInt()
            val mapUtils = MapUtils()

            googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    map.context, R.raw.map_style
                )
            )

            val routePoints = mapUtils.generateRouteByTwoPints(markers[0], markers[1])

            markers.forEach {
                googleMap.addMarker(
                    MarkerOptions().position(it).title("")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
                )
            }

            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngBounds(
                    LatLngBounds.builder().apply {
                        include(routePoints[0])
                        include(routePoints[routePoints.size - 1])
                    }.build(),
                    padding
                )
            )

            googleMap.addPolyline(PolylineOptions().apply { addAll(routePoints) }
                .width(7f).color(Color.GRAY).geodesic(false)
                .pattern(listOf(Dash(30f), Gap(20f))))

            mapUtils.setAnimation(
                googleMap,
                routePoints,
                BitmapFactory.decodeResource(map.context.resources, R.drawable.ic_plane)
            )
        }
    }
}
package `fun`.veres.mobileprj.uicomponents

import `fun`.veres.mobileprj.utils.lifecycle.FragmentLifecycleObserver
import `fun`.veres.mobileprj.utils.lifecycle.ViewLifecycleHandler
import android.content.Context
import android.util.AttributeSet
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng

class AtMap @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : MapView(context, attrs, defStyleAttr), ViewLifecycleHandler {

    init {
        FragmentLifecycleObserver.registerActionHandler(this)

    }

    override fun onViewResume() {
        onResume()
    }

    override fun onViewPause() {
        onPause()
    }

    override fun onViewDestroy() {
        onDestroy()
    }

    fun setMarkers(markers: List<LatLng>) {

    }

}
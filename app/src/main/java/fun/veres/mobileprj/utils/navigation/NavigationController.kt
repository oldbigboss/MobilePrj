package `fun`.veres.mobileprj.utils.navigation

import `fun`.veres.mobileprj.ui.destination.SetupDestinationFragment
import `fun`.veres.mobileprj.ui.map.MapFragment
import `fun`.veres.mobileprj.utils.animations.CustomTransactions
import `fun`.veres.mobileprj.utils.animations.StandardTransition
import com.google.android.gms.maps.model.LatLng

class NavigationController : BaseNavigationController() {

    companion object {
        private lateinit var instance: NavigationController

        fun getInstance(): NavigationController {
            if (!this::instance.isInitialized) {
                instance = NavigationController()
            }
            return instance
        }
    }

    fun openSetupDestinationFragment() =
        newFlow(SetupDestinationFragment(), StandardTransition.open())

    fun openMapFragment(locations: List<LatLng>) = addFragment(
        MapFragment.newInstance(locations), animation =  CustomTransactions.slideFromLeft()
    )

}
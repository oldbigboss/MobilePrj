package `fun`.veres.mobileprj.ui.map

import `fun`.veres.mobileprj.ui.base.BaseViewModel
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng

class MapViewModel : BaseViewModel() {

    val locations: MutableLiveData<List<LatLng>> = MutableLiveData()

    fun onBackPressed() = navigationController.onBackPressed()

}
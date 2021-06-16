package `fun`.veres.mobileprj.ui.destination

import `fun`.veres.mobileprj.R
import `fun`.veres.mobileprj.domain.destination.DestinationBusiness
import `fun`.veres.mobileprj.domain.destination.GetDestinationUseCase
import `fun`.veres.mobileprj.ui.base.BaseViewModel
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetupDestinationViewModel @Inject constructor(
    private val useCase: GetDestinationUseCase
) : BaseViewModel() {

    val destinationFrom = MutableLiveData("")
    val destinationTo = MutableLiveData("")

    val destinationViewItems = MutableLiveData(listOf<DestinationBusiness>())

    val isLoading = MutableLiveData(false)
    val isErrorReceived = MutableLiveData(false)

    val fromEditTextFocusChangeListener =
        View.OnFocusChangeListener { _, hasFocus ->
            clearItems()
            isFromEditTextOnFocus = hasFocus
            isToEditTextOnFocus = !hasFocus
        }
    val toEditTextFocusChangeListener =
        View.OnFocusChangeListener { _, hasFocus ->
            clearItems()
            isToEditTextOnFocus = hasFocus
            isFromEditTextOnFocus = !hasFocus
        }

    private var latLngFrom: LatLng? = null
    private var latLngTo: LatLng? = null

    private var isFromEditTextOnFocus = false
    private var isToEditTextOnFocus = false

    private var itemsList = listOf<DestinationBusiness>()

    init {
        destinationFrom.observeForever {
            if (itemsList.find { item -> item.name == it } == null) latLngFrom = null
            loadDestinationInfo(it)
        }
        destinationTo.observeForever {
            if (itemsList.find { item -> item.name == it } == null) latLngTo = null
            loadDestinationInfo(it)
        }
    }

    fun onConfirmDestinationClicked() {
        if (latLngFrom == null || latLngTo == null) navigationController.makeToast(
            R.string.please_pick_your_destination
        ) else if (latLngFrom == latLngTo) navigationController.makeToast(
            R.string.please_pick_different_cities
        ) else navigationController.openMapFragment(listOf(latLngFrom!!, latLngTo!!))
    }

    fun onDestinationClicked(destination: DestinationBusiness) {
        if (isFromEditTextOnFocus) {
            destinationFrom.postValue(destination.name)
            latLngFrom = destination.latLng
        } else {
            destinationTo.postValue(destination.name)
            latLngTo = destination.latLng
        }
        clearItems()
    }

    fun onRetryClicked() {
        isErrorReceived.postValue(false)
        if (isFromEditTextOnFocus) loadDestinationInfo(destinationFrom.value ?: "")
        else (loadDestinationInfo(destinationTo.value ?: ""))
    }

    private fun loadDestinationInfo(destination: String) {
        isLoading.postValue(true)
        viewModelScope.launch {
            try {
                useCase.invoke(destination).let { viewItems ->
                    //Делаем проверку, чтобы в Rv не летел результат, который уже выбран
                    if (viewItems.all { it.name != destination }) {
                        destinationViewItems.postValue(viewItems)
                        itemsList = viewItems
                    }
                }
            } catch (t: Throwable) {
                //В полноценном проекте надо трекать по смраткастам тип эксепшена,
                // и если не HttpException - кидать в крашлитику, но это не полноценный проект))
                t.printStackTrace()
                isErrorReceived.postValue(true)
            } finally {
                isLoading.postValue(false)
            }
        }
    }

    private fun clearItems() = destinationViewItems.postValue(listOf())

}
package `fun`.veres.mobileprj.ui.base

import `fun`.veres.mobileprj.utils.navigation.NavigationController
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(), Observable {

    @Transient
    private var callbacks: PropertyChangeRegistry? = null

    val navigationController = NavigationController.getInstance()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (callbacks == null) {
                callbacks = PropertyChangeRegistry()
            }
        }
        callbacks!!.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        synchronized(this) {
            if (callbacks == null) {
                return
            }
        }
        callbacks!!.remove(callback)
    }

    /**
     * Уведомляем слушателей, что все проперти данного инстанса изменились
     */
    fun notifyChange() {
        synchronized(this) {
            if (callbacks == null) {
                return
            }
        }
        callbacks!!.notifyCallbacks(this, 0, null)
    }


}
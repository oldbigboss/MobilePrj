package `fun`.veres.mobileprj.utils.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Утилита, позволяющая инкапсулировать отслеживание жизненого цикла активити внутрь наследника ui компонента,
 * который зависим от этого цикла
 * */

interface ViewLifecycleHandler {
    fun onViewCreate() {}
    fun onViewStart() {}
    fun onViewResume() {}
    fun onViewPause() {}
    fun onViewStop() {}
    fun onViewDestroy() {}
}

object FragmentLifecycleObserver : LifecycleObserver {

    private var lifecycleHandler: ViewLifecycleHandler? = null

    fun registerActionHandler(handler: ViewLifecycleHandler) {
        lifecycleHandler = handler
    }

    fun registerLifecycle(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        lifecycleHandler?.onViewCreate()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        lifecycleHandler?.onViewStart()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        lifecycleHandler?.onViewResume()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        lifecycleHandler?.onViewPause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        lifecycleHandler?.onViewStop()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        lifecycleHandler?.onViewDestroy()
    }

}
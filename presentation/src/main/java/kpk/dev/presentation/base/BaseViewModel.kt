package kpk.dev.presentation.base

import androidx.lifecycle.*
import kpk.dev.presentation.navigation.NavEvent
import kpk.dev.presentation.navigation.NavigationEvent

abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    private val navigationLiveData = NavigationEvent()

    fun subscribe(observer: Observer<NavEvent>, lifecycleOwner: LifecycleOwner) {
        navigationLiveData.observe(lifecycleOwner, observer)
    }

    fun publish(navEvent: NavEvent) {
        navigationLiveData.value = navEvent
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {}
}
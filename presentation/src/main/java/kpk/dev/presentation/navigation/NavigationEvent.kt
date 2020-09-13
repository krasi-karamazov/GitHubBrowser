package kpk.dev.presentation.navigation

import kpk.dev.presentation.utils.SingleLiveEvent


/**
 * An event class which we use in the Event based variant in the project.
 * A subclass of SingleLiveEvent typed with NavEvent which contains the destination and optional data
 */
class NavigationEvent : SingleLiveEvent<NavEvent>()
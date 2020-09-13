package kpk.dev.presentation.navigation

import android.os.Parcelable


data class NavEvent(val navDirections: NavigationDirs, val data: Parcelable? = null)

enum class NavigationDirs {
    REPOS,
    COMMITS
}
package kpk.dev.data.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutineDispatcherProvider {
    val main: CoroutineDispatcher = Dispatchers.Main
    val iO: CoroutineDispatcher = Dispatchers.IO
}
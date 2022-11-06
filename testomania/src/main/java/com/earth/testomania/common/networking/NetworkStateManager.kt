package com.earth.testomania.common.networking

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vazhapp on 23.10.2022
 */
@Singleton
class NetworkStateManager @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) {

    private val activeNetworkStatus = MutableStateFlow(false)

    fun setNetworkConnectivityStatus(connectivityStatus: Boolean) {
        CoroutineScope(dispatcher).launch {
            activeNetworkStatus.emit(connectivityStatus)
        }
    }

    fun getNetworkConnectivityStatus(): StateFlow<Boolean> {
        return activeNetworkStatus.asStateFlow()
    }
}
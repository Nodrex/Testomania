package com.earth.testomania.common.networking

import kotlinx.coroutines.flow.Flow

/**
 * Created by Vazhapp on 20.11.2022
 */
interface ConnectivityObserver {
    fun observe(): Flow<ConnectionState>

    enum class ConnectionState {
        Available, Unavailable
    }
}
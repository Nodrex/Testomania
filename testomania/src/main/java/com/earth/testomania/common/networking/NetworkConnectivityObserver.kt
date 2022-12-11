package com.earth.testomania.common.networking

import android.content.Context
import android.net.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vazhapp on 23.10.2022
 */
@Singleton
class NetworkConnectivityObserver @Inject constructor(
    @ApplicationContext context: Context,
) : ConnectivityObserver {

    private var connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<ConnectivityObserver.ConnectionState> {
        return callbackFlow {
            val callBack = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { trySend(ConnectivityObserver.ConnectionState.Available) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { trySend(ConnectivityObserver.ConnectionState.Unavailable) }
                }
            }
            // guess immediately device is connected to the internet or not
            launch { trySend(checkCurrentNetworkState()) }

            connectivityManager.registerDefaultNetworkCallback(callBack)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callBack)
            }
        }.distinctUntilChanged()
    }

    // If active network is null device isn't connected to the internet
    private fun checkCurrentNetworkState(): ConnectivityObserver.ConnectionState {
        return if (connectivityManager.activeNetwork == null) {
            ConnectivityObserver.ConnectionState.Unavailable
        } else {
            ConnectivityObserver.ConnectionState.Available
        }
    }
}
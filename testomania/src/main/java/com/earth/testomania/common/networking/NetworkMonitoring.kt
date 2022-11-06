package com.earth.testomania.common.networking

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log.d
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vazhapp on 23.10.2022
 */
@Singleton
class NetworkMonitoring @Inject constructor(
    @ApplicationContext context: Context,
    private val networkStateManager: NetworkStateManager
) : ConnectivityManager.NetworkCallback() {

    private var networkRequest: NetworkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    private var connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        networkStateManager.setNetworkConnectivityStatus(true)
        d("VazhappNetworkManager", "Network Available")
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        networkStateManager.setNetworkConnectivityStatus(false)
        d("VazhappNetworkManager", "Network Unavailable")
    }

    /**
     * We must register it only once
     */
    fun registerNetworkCallbacks() {
        connectivityManager.registerNetworkCallback(networkRequest, this)
    }
}
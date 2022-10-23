package com.earth.testomania.common.networking

import android.content.Context
import android.net.*
import android.util.Log.d

/**
 * Created by Vazhapp on 23.10.2022
 */
class NetworkMonitoring(
    context: Context
) : ConnectivityManager.NetworkCallback() {

    private var mNetworkRequest: NetworkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    private var mConnectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    override fun onAvailable(network: Network) {
        super.onAvailable(network)

        d("VazhappNetworkManager", "Network Available")

    }

    override fun onUnavailable() {
        super.onUnavailable()

        d("VazhappNetworkManager", "Network Unavailable")
    }

    /**
     * We must register it only once
     */
    fun registerNetworkCallbacks() {
        mConnectivityManager.registerNetworkCallback(mNetworkRequest, this)
    }
}
package com.capgemini.capcars.data.network.monitoring

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.compose.runtime.MutableState

/**
 * A BroadcastReceiver that listens for network status changes.
 * When a broadcast with the action "com.capcars.NETWORK_STATUS" is received,
 * it checks the current network connection and updates the `isConnected` state.
 *
 * @param isConnected A MutableState that holds the current network connection status.
 *                    It will be updated to `true` if the device is connected to the network,
 *                    and `false` otherwise.
 */

class NetworkStatusReceiver(private val isConnected: MutableState<Boolean>) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        isConnected.value = networkInfo != null && networkInfo.isConnected
    }
}
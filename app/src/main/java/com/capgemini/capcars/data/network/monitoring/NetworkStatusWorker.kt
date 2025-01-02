package com.capgemini.capcars.data.network.monitoring

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * A Worker class that checks the network availability in the background.
 * If the network is unavailable, it sends a local broadcast with the action
 * "com.capcars.NETWORK_STATUS" to notify other components in the app.
 */

class NetworkStatusWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        if (!isNetworkAvailable()) {
            val intent = Intent("com.capcars.NETWORK_STATUS")
            // Send a local broadcast if the network is unavailable
            LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
        }
        return Result.success()
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        // Get the network capabilities for the active network. Return false if no capabilities.
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}


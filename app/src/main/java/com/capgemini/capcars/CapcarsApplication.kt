package com.capgemini.capcars

import android.app.Application
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.capgemini.capcars.data.network.monitoring.NetworkStatusWorker
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class CapcarsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Timber for logging in debug builds
        Timber.plant(Timber.DebugTree())

        // Schedule the network status check every 5 seconds
        val networkStatusWorkRequest = PeriodicWorkRequestBuilder<NetworkStatusWorker>(5, TimeUnit.SECONDS).build()
        WorkManager.getInstance(this).enqueue(networkStatusWorkRequest)
    }
}
package com.capgemini.capcars

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CapcarsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize Timber for logging in debug builds
        Timber.plant(Timber.DebugTree())
    }
}
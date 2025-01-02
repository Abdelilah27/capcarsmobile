package com.capgemini.capcars

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.capgemini.capcars.data.network.monitoring.NetworkStatusReceiver
import com.capgemini.capcars.presentation.ui.navigation.AppNavHost
import com.capgemini.capcars.presentation.ui.theme.CapcarsTheme
import com.capgemini.commons.ui.components.NetworkStatusSnackbar
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var networkStatusReceiver: NetworkStatusReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the default locale to French
        setLocaleToFrench()

        enableEdgeToEdge()
        setContent {
            CapcarsTheme {
                val navController = rememberNavController()
                val isConnected = remember { mutableStateOf(true) }

                // Register the network status receiver
                networkStatusReceiver = NetworkStatusReceiver(isConnected)
                val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
                registerReceiver(networkStatusReceiver, intentFilter)

                AppNavHost(navController = navController)
                NetworkStatusSnackbar(isConnected, stringResource(R.string.lost_connection_message))
            }
        }
    }

    private fun setLocaleToFrench() {
        val locale = Locale("fr")
        Locale.setDefault(locale)

        // Create a new configuration with the desired locale
        val config = resources.configuration
        config.setLocale(locale)

        // Update the resources with the new configuration
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Unregister the receiver when the activity is destroyed
        unregisterReceiver(networkStatusReceiver)
    }
}
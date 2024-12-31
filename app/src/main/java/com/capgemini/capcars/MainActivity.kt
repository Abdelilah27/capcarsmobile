package com.capgemini.capcars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.capgemini.capcars.ui.navigation.AppNavHost
import com.capgemini.capcars.ui.theme.CapcarsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // TODO SCAT
            CapcarsTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
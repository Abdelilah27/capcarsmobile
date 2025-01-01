package com.capgemini.capcars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.capgemini.capcars.presentation.ui.navigation.AppNavHost
import com.capgemini.capcars.presentation.ui.theme.CapcarsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CapcarsTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
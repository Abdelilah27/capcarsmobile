package com.capgemini.capcars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.capgemini.capcars.ui.carsList.CarsListScreen
import com.capgemini.capcars.ui.onBoarding.OnBoardingScreen
import com.capgemini.capcars.ui.theme.CapcarsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // TODO SCAT
            CapcarsTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "onboarding_screen") {
                    composable("onboarding_screen") {
                        OnBoardingScreen(navController = navController)
                    }
                    composable("cars_list_screen") {
                        CarsListScreen(navController = navController)
                    }
                }
            }
        }
    }
}
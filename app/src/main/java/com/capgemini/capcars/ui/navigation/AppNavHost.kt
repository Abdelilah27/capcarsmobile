package com.capgemini.capcars.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.capgemini.capcars.ui.carsList.CarListScreen
import com.capgemini.capcars.ui.onboarding.OnboardingScreen

// AppNavHost.kt
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Onboarding.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(NavigationItem.CarList.route) {
            CarListScreen(navController)
        }
    }
}

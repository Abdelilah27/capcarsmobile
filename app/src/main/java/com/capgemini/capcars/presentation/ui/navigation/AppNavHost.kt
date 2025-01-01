package com.capgemini.capcars.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.capgemini.capcars.presentation.ui.carList.CarListScreen
import com.capgemini.capcars.presentation.ui.carList.CarListViewModel
import com.capgemini.capcars.presentation.ui.onboarding.OnboardingScreen
import com.capgemini.commons.ui.animation.NavigationAnimations

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
        composable(
            route = NavigationItem.Onboarding.route
        ) {
            OnboardingScreen(
                onNavigate = { navController.navigate(NavigationItem.CarList.route) }
            )
        }

        composable(
            route = NavigationItem.CarList.route,
            enterTransition = { NavigationAnimations.slideInSpec },
            exitTransition = { NavigationAnimations.slideOutSpec },
            popEnterTransition = { NavigationAnimations.reverseSlideInSpec },
            popExitTransition = { NavigationAnimations.reverseSlideOutSpec }
        ) {
            val viewModel: CarListViewModel = hiltViewModel()
            val carListState by viewModel.state.collectAsState()
            CarListScreen(
                carListState = carListState,
                onBackClicked = { navController.popBackStack() },
                onRetryClicked = { viewModel.retryFetchCars() }
            )
        }
    }
}
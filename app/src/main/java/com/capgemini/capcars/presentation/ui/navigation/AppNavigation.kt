package com.capgemini.capcars.presentation.ui.navigation

/**
 * Enum class defining the different screens in the app.
 */

enum class Screen {
    ONBOARDING,
    CAR_LIST
}

/**
 * Sealed class representing the different navigation items/routes in the app.
 * Each screen has a corresponding route.
 */

sealed class NavigationItem(val route: String) {
    object Onboarding : NavigationItem(Screen.ONBOARDING.name)
    object CarList : NavigationItem(Screen.CAR_LIST.name)
}

package com.capgemini.capcars.ui.navigation

enum class Screen {
    ONBOARDING,
    CAR_LIST
}

sealed class NavigationItem(val route: String) {
    object Onboarding : NavigationItem(Screen.ONBOARDING.name)
    object CarList : NavigationItem(Screen.CAR_LIST.name)
}

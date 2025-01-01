package com.capgemini.commons.ui.animation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

/**
 * A collection of slide-in and slide-out animations used for screen transitions in the app.
 * These animations are configured for horizontal sliding, both for entering and exiting screens.
 */

object NavigationAnimations {

    // Slide-in animation (entering the screen)
    val slideInSpec = slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(durationMillis = 300) // Customize duration
    )

    // Slide-out animation (exiting the screen)
    val slideOutSpec = slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(durationMillis = 300)
    )

    // Reverse slide-in animation (for popping back)
    val reverseSlideInSpec = slideInHorizontally(
        initialOffsetX = { -it }, // Reverse direction
        animationSpec = tween(durationMillis = 300)
    )

    // Reverse slide-out animation (for popping back)
    val reverseSlideOutSpec = slideOutHorizontally(
        targetOffsetX = { it }, // Reverse direction
        animationSpec = tween(durationMillis = 300)
    )
}
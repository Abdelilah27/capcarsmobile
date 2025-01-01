package com.capgemini.commons.ui.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.capgemini.commons.ui.theme.OnPrimary
import com.capgemini.commons.ui.theme.iconSize
import kotlinx.coroutines.delay

/**
 * A composable function that animates an icon sliding out of the screen horizontally.
 * The icon moves from its original position to the right side of the screen and triggers
 * a navigation action once the animation completes.
 *
 * @param icon The [Painter] for the icon to be displayed.
 * @param moveIcon A Boolean flag to control whether the icon should slide out of the screen.
 * @param durationMillis The duration of the slide-out animation in milliseconds (default is 1500).
 * @param onNavigate A lambda function that triggers navigation once the animation is complete.
 */

@Composable
fun SlideOutIcon(
    icon: Painter,
    moveIcon: Boolean,
    durationMillis: Int = 1500,
    onNavigate: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val animatedOffset by animateDpAsState(
        targetValue = if (moveIcon) screenWidth else 0.dp,
        animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)
    )

    // Apply animation to the icon
    Icon(
        painter = icon,
        contentDescription = null,
        tint = OnPrimary,
        modifier = Modifier
            .size(iconSize)
            .offset(x = animatedOffset) // Apply horizontal movement
    )

    // Trigger navigation after animation ends
    LaunchedEffect(moveIcon) {
        if (moveIcon) {
            delay(durationMillis.toLong())
            onNavigate()
        }
    }
}


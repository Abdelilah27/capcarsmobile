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

    // Animate icon's movement horizontally
    Icon(
        painter = icon,
        contentDescription = null,
        tint = OnPrimary,
        modifier = Modifier
            .size(iconSize)
            .offset(x = animatedOffset)
    )

    // Trigger navigation after animation ends
    LaunchedEffect(moveIcon) {
        if (moveIcon) {
            delay(durationMillis.toLong())
            onNavigate()
        }
    }
}


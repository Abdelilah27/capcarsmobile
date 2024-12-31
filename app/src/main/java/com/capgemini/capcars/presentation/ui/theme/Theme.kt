package com.capgemini.capcars.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.capgemini.commons.ui.theme.*

private val lightScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    onSecondary = OnSecondary,
    background = Background,
    surface = Surface,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant
)


@Composable
fun CapcarsTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = lightScheme,
        typography = Typography,
        shapes = shapes,  // TODO SCAT
        content = content
    )
}
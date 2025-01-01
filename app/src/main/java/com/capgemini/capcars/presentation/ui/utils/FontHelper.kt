package com.capgemini.capcars.presentation.ui.utils

import androidx.compose.ui.unit.TextUnit

/**
 * Determines the appropriate font size based on the screen width.
 *
 * @param screenWidth The width of the screen in pixels.
 * @param smallSize The font size to use for small screens.
 * @param largeSize The font size to use for large screens.
 * @return The selected font size based on the screen width.
 */

fun getFontSize(screenWidth: Int, smallSize: TextUnit, largeSize: TextUnit): TextUnit {
    return if (screenWidth < 420) smallSize else largeSize
}
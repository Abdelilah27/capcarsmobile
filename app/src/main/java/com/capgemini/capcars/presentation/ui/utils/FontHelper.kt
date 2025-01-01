package com.capgemini.capcars.presentation.ui.utils

import androidx.compose.ui.unit.TextUnit

fun getFontSize(screenWidth: Int, smallSize: TextUnit, largeSize: TextUnit): TextUnit {
    return if (screenWidth < 420) smallSize else largeSize
}
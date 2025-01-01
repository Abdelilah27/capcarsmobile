package com.capgemini.capcars.presentation.ui.theme

import androidx.compose.runtime.compositionLocalOf

/**
 * CompositionLocal that holds the current screen width in density-independent pixels (dp).
 * Initially set to 0. It used to access the screen width throughout the composables.
 */

val LocalScreenWidthDp = compositionLocalOf { 0 }
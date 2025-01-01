package com.capgemini.commons.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes

/**
 * A collection of predefined shapes used for consistent corner radius styling throughout the app.
 * These shapes are applied to UI elements with different corner radius values, enhancing the UI design.
 *
 * The shapes use `RoundedCornerShape` to define the corner radius for small, medium, large, and extra-large elements.
 */

val shapes = Shapes(
    small = RoundedCornerShape(smallShapeRadius),
    medium = RoundedCornerShape(mediumShapeRadius),
    large = RoundedCornerShape(largeShapeRadius),
    extraLarge = RoundedCornerShape(extraLargeShapeRadius)
)
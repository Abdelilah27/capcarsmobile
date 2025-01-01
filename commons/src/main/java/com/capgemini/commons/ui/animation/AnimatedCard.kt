package com.capgemini.commons.ui.animation

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.capgemini.commons.ui.theme.Background
import com.capgemini.commons.ui.theme.cardElevation
import com.capgemini.commons.ui.theme.largeShapeRadius

/**
 * Composable function to display an animated card with fade-in and slide-in effects.
 *
 * @param modifier Modifier to be applied to the card.
 * @param content The composable content to display inside the card.
 */

@Composable
fun AnimatedCardContent(
    modifier: Modifier = Modifier,
    content: @Composable (Modifier) -> Unit
) {
    var cardVisibility by remember { mutableStateOf(false) }

    // Set up the slide and fade-in animation when the card enters the screen
    val transition = updateTransition(targetState = cardVisibility, label = "Card Transition")

    // Fade-in animation with increased opacity for a clearer effect
    val fadeIn = transition.animateFloat(
        label = "Fade In",
        transitionSpec = { tween(durationMillis = 1000) } // Increased duration for more visibility
    ) { state -> if (state) 1f else 0f } // From 0 to 1 (strong fade effect)

    // Slide-in animation from bottom with increased distance for more noticeable effect
    val slideIn = transition.animateDp(
        label = "Slide In",
        transitionSpec = { tween(durationMillis = 1000) } // Increased duration
    ) { state -> if (state) 0.dp else 150.dp } // Increased slide distance for clearer effect

    // Trigger animation on the first composition with a static key
    LaunchedEffect(key1 = "key") {
        cardVisibility = true
    }

    // Apply the animation to the card
    Card(
        modifier = modifier
            .graphicsLayer(
                alpha = fadeIn.value, // Apply fade-in effect
                translationY = slideIn.value.value // Apply sliding effect
            )
            .background(Background),
        shape = RoundedCornerShape(largeShapeRadius),
        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation)
    ) {
        content(Modifier)
    }
}
package com.capgemini.commons.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.capgemini.commons.ui.theme.OnSecondary
import com.capgemini.commons.ui.theme.Secondary
import com.capgemini.commons.ui.theme.SubHeadlineSmall
import com.capgemini.commons.ui.theme.largeShapeRadius
import com.capgemini.commons.ui.theme.smallSpacing

@Composable
fun NetworkStatusSnackbar(
    isConnected: MutableState<Boolean>,
    message: String,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(isConnected.value) {
        if (!isConnected.value) {
            snackbarHostState.showSnackbar(message)
        }
    }

    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { snackbarData ->
            Snackbar(
                modifier = Modifier.padding(smallSpacing),
                shape = RoundedCornerShape(largeShapeRadius),
                containerColor = Secondary,
                contentColor = OnSecondary
            ) {
                Text(
                    text = snackbarData.visuals.message,
                    color = OnSecondary,
                    style = SubHeadlineSmall
                )
            }
        },
        modifier = modifier
    )
}
package com.capgemini.commons.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.capgemini.commons.ui.theme.BodySmall
import com.capgemini.commons.ui.theme.LightGray
import com.capgemini.commons.ui.theme.OnSurface
import com.capgemini.commons.ui.theme.Surface
import com.capgemini.commons.ui.theme.loadingStrokeWidth
import com.capgemini.commons.ui.theme.mediumSpacing
import com.capgemini.commons.ui.theme.progressIndicatorSize

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    message: String,
    textStyle: TextStyle = BodySmall
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = modifier
                    .background(Surface, shape = CircleShape)
                    .size(progressIndicatorSize)
                    .padding(mediumSpacing),
                strokeWidth = loadingStrokeWidth,
                color = OnSurface,
                trackColor = LightGray
            )

            Spacer(modifier = Modifier.height(mediumSpacing))

            Text(
                text = message,
                style = textStyle,
            )
        }
    }
}

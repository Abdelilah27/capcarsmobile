package com.capgemini.commons.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capgemini.commons.ui.theme.OnSecondary
import com.capgemini.commons.ui.theme.PrimaryButtonText
import com.capgemini.commons.ui.theme.Secondary
import com.capgemini.commons.ui.theme.largeShapeRadius

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = buttonColors(
            containerColor = Secondary,
            contentColor = OnSecondary
        ),
        shape = RoundedCornerShape(largeShapeRadius)
    ) {
        Text(
            text = text.uppercase(),
            style = PrimaryButtonText,
            modifier = Modifier.padding(8.dp)
        )
    }
}
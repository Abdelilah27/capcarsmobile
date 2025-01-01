package com.capgemini.commons.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.capgemini.commons.R
import com.capgemini.commons.ui.theme.Secondary
import com.capgemini.commons.ui.theme.SubHeadlineMedium
import com.capgemini.commons.ui.theme.iconSize
import com.capgemini.commons.ui.theme.mediumSpacing

@Composable
fun CustomBackHeader(
    onBackClicked: () -> Unit,
    title: String? = null,
    titleStyle: TextStyle = SubHeadlineMedium
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back arrow icon
        Icon(
            painter = painterResource(id = R.drawable.baseline_keyboard_backspace_24),
            tint = Secondary,
            contentDescription = "Back",
            modifier = Modifier
                .size(iconSize)
                .clickable { onBackClicked() }
        )
        // Optional title text
        title?.let {
            Spacer(modifier = Modifier.width(mediumSpacing))
            Text(
                text = it,
                style = titleStyle
            )
        }
    }
}
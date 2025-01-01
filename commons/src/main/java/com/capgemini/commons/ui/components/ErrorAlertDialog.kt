package com.capgemini.commons.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.capgemini.commons.R
import com.capgemini.commons.ui.theme.SubHeadlineExtraLarge
import com.capgemini.commons.ui.theme.SubHeadlineSmall
import com.capgemini.commons.ui.theme.largeShapeRadius

@Composable
fun ErrorAlertDialog(
    errorMessage: String,
    onRetry: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(id = R.string.error_title),
                style = SubHeadlineExtraLarge
            )
        },
        text = {
            Text(
                text = errorMessage,
                style = SubHeadlineSmall
            )
        },
        confirmButton = {
            RetryButton(
                onClick = onRetry,
                text = stringResource(id = R.string.retry_button_text)
            )
        },
        dismissButton = {
            DismissButton(
                onClick = onDismiss,
                text = stringResource(id = R.string.dismiss_button_text)
            )
        },
        shape = RoundedCornerShape(largeShapeRadius)
    )
}
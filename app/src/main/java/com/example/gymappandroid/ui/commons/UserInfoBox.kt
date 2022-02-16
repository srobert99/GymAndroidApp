package com.example.gymappandroid.ui.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun UserInfoBox(
    modifier: Modifier = Modifier.fillMaxWidth(),
    labelText: String,
    leadingIcon: ImageVector,
    isNumber: Boolean = false,
    onValueChange: (String) -> Unit,
    currentText: String,
    hasError: Boolean = false
) {
    OutlinedTextField(
        value = currentText,
        onValueChange = { text ->
            onValueChange(text)
        },
        label = { Text(labelText) },
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null
            )
        },
        singleLine = true,
        isError = hasError,
        keyboardOptions = if (isNumber) KeyboardOptions(keyboardType = KeyboardType.Number)
        else KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        modifier = modifier
    )
}
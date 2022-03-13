package com.example.gymappandroid.ui.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun UserInfoBox(
    labelText: String,
    readOnly: Boolean = true,
    leadingIcon: ImageVector,
    isNumber: Boolean = false,
    onValueChange: (String) -> Unit,
    currentText: String,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    hasError: Boolean = false
) {
    OutlinedTextField(
        colors = colors,
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
        readOnly = readOnly,
        enabled = !readOnly,
        singleLine = true,
        isError = hasError,
        keyboardOptions = if (isNumber) KeyboardOptions(keyboardType = KeyboardType.Number)
        else KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        modifier = Modifier.fillMaxWidth(),
    )
}
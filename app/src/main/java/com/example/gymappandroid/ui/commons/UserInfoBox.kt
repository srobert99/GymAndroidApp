package com.example.gymappandroid.ui.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.CombinedModifier
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import com.example.gymappandroid.ui.account.auth.AuthViewModel

@Composable
fun UserInfoBox(
    modifier: Modifier = Modifier.fillMaxWidth(),
    labelText: String,
    leadingIcon: ImageVector,
    isNumber: Boolean = false,
    onValueChange: (String) -> Unit,
    currentText: String,
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
        keyboardOptions = if (isNumber) KeyboardOptions(keyboardType = KeyboardType.Number)
        else KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        modifier = modifier
    )
}
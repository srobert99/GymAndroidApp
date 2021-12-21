package com.example.gymappandroid.ui.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.gymappandroid.R

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    labelText: String = "Password",
    currentText: String = "",
    onPasswordChange: (String) -> Unit
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.design_ic_visibility)
    else
        painterResource(id = R.drawable.design_ic_visibility_off)

    OutlinedTextField(
        value = currentText,
        onValueChange = onPasswordChange,
        placeholder = { Text(text = labelText) },
        label = { Text(text = labelText) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = null
            )
        },
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility = !passwordVisibility
            }) {
                Icon(
                    painter = icon,
                    contentDescription = "Visibility Icon"
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (passwordVisibility) VisualTransformation.None
        else PasswordVisualTransformation(),
        modifier = modifier
    )
}
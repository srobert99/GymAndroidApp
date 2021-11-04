package com.example.gymappandroid.ui.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.gymappandroid.R

@Composable
fun UserInfoBox(labelText: String, leadingIcon: ImageVector, isNumber: Boolean = false) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
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
        )
    )
}


@Composable
fun PasswordTextField(labelText: String = "Password") {
    Column {
        var password by rememberSaveable { mutableStateOf("") }
        var passwordVisibility by remember { mutableStateOf(false) }

        val icon = if (passwordVisibility)
            painterResource(id = R.drawable.design_ic_visibility)
        else
            painterResource(id = R.drawable.design_ic_visibility_off)

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
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
            else PasswordVisualTransformation()
        )
    }
}
package com.example.gymappandroid.ui.commons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymappandroid.ui.theme.DarkBlue
import com.example.gymappandroid.ui.theme.NeonBlue
import com.example.gymappandroid.ui.theme.Purple700
import com.example.gymappandroid.ui.theme.White

private val IconButtonSizeModifier = Modifier.height(50.dp)

@Composable
fun RoundedToggleButton(
    state: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    activeColor: Color = NeonBlue,
    inactiveColor: Color = DarkBlue,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier then IconButtonSizeModifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (state) activeColor else inactiveColor,
            contentColor = if (state) DarkBlue else Color.Gray
        )
    ) {
        Text(text = text, fontSize = 16.sp, color = White)
    }
}
package com.example.gymappandroid.data.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class CoinOffer(
    val amount: Int = 0,
    val price:Int = 0,
    val icon: ImageVector = Icons.Filled.Star
)
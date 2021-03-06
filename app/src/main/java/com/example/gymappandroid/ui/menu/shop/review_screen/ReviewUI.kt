package com.example.gymappandroid.ui.menu.shop.product_details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gymappandroid.R
import com.example.gymappandroid.data.models.Review

@Composable
fun ReviewUI(review: Review) {
    Column(
        modifier = Modifier
            .width(250.dp)
            .background(colorResource(id = R.color.teal_700))
    ) {
        Row() {
            for (i in 1..review.stars) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = null)
            }
            Spacer(modifier = Modifier.size(50.dp))
            Text(review.username, color = Color.White, fontWeight = FontWeight.Bold)
        }
        Text(review.comment, color = Color.White)
    }
}

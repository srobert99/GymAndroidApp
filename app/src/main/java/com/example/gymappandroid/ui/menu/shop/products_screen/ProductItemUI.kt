package com.example.gymappandroid.ui.menu.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.gymappandroid.R
import com.example.gymappandroid.data.models.Product
import com.example.gymappandroid.navigation.Screen

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductItemUi(product: Product, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .size(150.dp)
            .clickable { navController.navigate(Screen.ProductDetails.route + "/${product.id}") },
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(12.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberImagePainter(
                    data = product.image.first(),
                    builder = { placeholder(R.drawable.logo) }),
                contentScale = ContentScale.FillBounds,
                contentDescription = "shop category 1",
                modifier = Modifier.weight(0.5f)
            )
            Text(
                product.model.uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier
                    .weight(0.1f)
                    .padding(2.dp),
                textAlign = TextAlign.Center
            )

        }
    }

}
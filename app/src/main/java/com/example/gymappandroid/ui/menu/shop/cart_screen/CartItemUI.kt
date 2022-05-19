package com.example.gymappandroid.ui.menu.shop.cart_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.gymappandroid.R
import com.example.gymappandroid.data.models.ShoppingCartItem


@OptIn(ExperimentalCoilApi::class)
@Composable
fun CartItemUI(shoppingCartItem: ShoppingCartItem) {
    Surface(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .background(Color.LightGray),
        color = Color.LightGray,
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize(),
            backgroundColor = Color.White
        ) {
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberImagePainter(data = shoppingCartItem.imageSource, builder = {
                        placeholder(R.drawable.logo)
                    }),
                    contentDescription = "product image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(5.dp)
                        .height(125.dp)
                        .width(150.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxSize(),
                ) {
                    Text(
                        shoppingCartItem.model,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(end = 20.dp)
                    )
                    Text(
                        "SIZE: ${shoppingCartItem.specification}",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "${shoppingCartItem.price} EUR",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(end = 20.dp)
                    )
                }
            }
        }
    }
}


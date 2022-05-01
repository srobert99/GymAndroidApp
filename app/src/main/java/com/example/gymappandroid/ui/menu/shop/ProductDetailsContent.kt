package com.example.gymappandroid.ui.menu.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.gymappandroid.data.models.Product
import com.example.gymappandroid.ui.theme.GymAppAndroidTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductDetailsContent(shopViewModel: ShopViewModel) {
    val product = Product(productType = "muie")
    GymAppAndroidTheme {
        androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
            ) {
                Image(
                    painter = rememberImagePainter(product.image),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "shop category 1",
                    modifier = Modifier
                        .weight(0.35f)
                        .fillMaxSize()
                )
                Text(
                    product.model,
                    modifier = Modifier
                        .weight(0.1f)
                        .padding(top = 20.dp)
                        .fillMaxSize(),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Column(modifier = Modifier.weight(0.3f)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Button(onClick = { /*TODO*/ }, modifier = Modifier.weight(0.9f)) {
                            Text("Here should be the sizes", fontSize = 25.sp)
                        }
                    }
                    Text(
                        product.price.toString(),
                        textAlign = TextAlign.End,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .weight(0.1f)
                            .fillMaxWidth()
                    )
                    Text(
                        product.description,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .weight(0.3f)
                            .fillMaxSize()
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(0.1f)
                        .fillMaxWidth()
                        .wrapContentSize()
                        .align(CenterHorizontally)
                ) {
                    Text(
                        "Add to shopping cart",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(5.dp)
                    )

                }

            }

        }
    }
}

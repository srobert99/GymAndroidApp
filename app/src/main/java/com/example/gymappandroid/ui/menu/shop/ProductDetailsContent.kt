package com.example.gymappandroid.ui.menu.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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

    val currentProduct by shopViewModel.selectedProduct.observeAsState(initial = Product())
    val isInLoadingState by shopViewModel.isOnLoadingState.observeAsState(initial = true)
    val x = listOf("M", "L", "S", "XS", "XL")

    GymAppAndroidTheme {
        androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {
            if (isInLoadingState) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                ) {
                    Image(
                        painter = rememberImagePainter(currentProduct.image),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "product 1",
                        modifier = Modifier
                            .weight(0.3f)
                            .fillMaxSize()
                    )
                    Text(
                        currentProduct.model,
                        modifier = Modifier
                            .weight(0.1f)
                            .padding(top = 20.dp)
                            .fillMaxSize(),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Column(modifier = Modifier.weight(0.3f)) {
                        Text("Available Sizes:")
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize()
                        ) {
                            SizeOptionList(listOfSizes = x)
                        }
                        Text(
                            currentProduct.price.toString() + " EURO",
                            textAlign = TextAlign.End,
                            fontSize = 15.sp,
                            modifier = Modifier
                                .weight(0.1f)
                                .fillMaxWidth()
                        )
                        Text(
                            currentProduct.description,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .weight(0.3f)
                                .fillMaxSize()
                        )
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .weight(0.05f)
                            .fillMaxWidth()
                            .wrapContentSize()
                            .align(CenterHorizontally)
                    ) {
                        Text(
                            "Add to shopping cart",
                            fontSize = 10.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
            }
        }
    }
}

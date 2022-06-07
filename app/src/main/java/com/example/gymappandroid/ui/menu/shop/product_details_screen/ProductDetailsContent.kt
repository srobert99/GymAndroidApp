package com.example.gymappandroid.ui.menu.shop

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.gymappandroid.R
import com.example.gymappandroid.data.models.Product
import com.example.gymappandroid.ui.commons.DotsIndicator
import com.example.gymappandroid.ui.menu.shop.product_details_screen.SizeOptionUI
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalCoilApi::class, ExperimentalPagerApi::class)
@Composable
fun ProductDetailsContent(
    userId: String,
    shopViewModel: ShopViewModel,
    selectedProduct: Product
) {
    val pagerState = rememberPagerState()
    val imageCount = selectedProduct.image.size
    var currentImage = selectedProduct.image.first()
    val currentSelectedSpecification by shopViewModel.currentSelectedProductSpecification.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .wrapContentSize()
            .verticalScroll(rememberScrollState())
    ) {
        HorizontalPager(count = imageCount, state = pagerState) { page ->
            Box(modifier = Modifier.wrapContentSize()) {
                Image(
                    painter = rememberImagePainter(
                        data = currentImage,
                        builder = { placeholder(R.drawable.logo) }),
                    contentDescription = "product image",
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(BottomCenter)
                ) {
                    if (imageCount > 1) {
                        DotsIndicator(
                            totalDots = 3,
                            selectedIndex = page,
                            selectedColor = Color.White,
                            unSelectedColor = Color.Gray
                        )
                    }
                }
            }
            currentImage = when (page) {
                1 -> selectedProduct.image[1]
                2 -> selectedProduct.image[2]
                else -> selectedProduct.image[0]
            }
        }
        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.teal_200))
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = selectedProduct.model,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = 10.dp)
            )
        }
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(colorResource(id = R.color.dark_blue))
                .padding(horizontal = 20.dp)
                .padding(top = 30.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(colorResource(id = R.color.teal_200))
                    .wrapContentSize()
            ) {
                Text(
                    text = "DESCRIPTION",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White,
                    modifier = Modifier
                        .padding(5.dp),
                )
            }
            Text(
                text = selectedProduct.description,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .padding(bottom = 30.dp),
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                fontSize = 15.sp
            )
            Text(
                text = "REVIEWS",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                color = Color.White,
                modifier = Modifier
                    .background(colorResource(id = R.color.teal_200))
                    .padding(5.dp)
            )
            Text(
                "No reviews yet",
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 30.dp)
                    .align(CenterHorizontally),
                color = Color.Gray,
                fontSize = 12.sp
            )
            Box(
                modifier = Modifier
                    .background(colorResource(id = R.color.teal_200))
                    .wrapContentSize()
            ) {
                Text(
                    text = "AVAILABLE SIZES",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White,
                    modifier = Modifier
                        .padding(5.dp),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 5.dp, bottom = 30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LazyRow(
                    contentPadding = PaddingValues(end = 20.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 20.dp)
                ) {
                    items(selectedProduct.availableSize) {
                        SizeOptionUI(
                            it.sizeName,
                            currentSelectedSpecification,
                            modifier = Modifier
                                .clickable { shopViewModel.selectSize(it.sizeName) }
                                .size(75.dp)
                                .padding(10.dp),
                        )
                    }
                }
                Text(
                    "${selectedProduct.price} \n EUR",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White
                )
            }

            Card(
                modifier = Modifier
                    .clickable { }
                    .align(CenterHorizontally)
                    .padding(bottom = 10.dp)
                    .wrapContentSize(),
                backgroundColor = colorResource(id = R.color.teal_200),
                contentColor = Color.White
            ) {
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(5.dp)
                        .background(colorResource(id = R.color.teal_200))
                        .clickable {
                            if (currentSelectedSpecification.isNotEmpty()) {
                                shopViewModel.addItemToShoppingCart(userId)
                            } else {
                                Toast
                                    .makeText(
                                        context,
                                        "Please select a size",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "ADD TO CART ",
                        fontSize = 15.sp,
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "shopping cart",
                        Modifier
                            .size(25.dp)
                    )
                }
            }
        }
    }
}


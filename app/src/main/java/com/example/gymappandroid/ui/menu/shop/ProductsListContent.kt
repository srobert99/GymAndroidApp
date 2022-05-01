package com.example.gymappandroid.ui.menu.shop

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsListContent(
    shopViewModel: ShopViewModel,
    navController: NavController,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            contentPadding = PaddingValues(16.dp),
            cells = GridCells.Adaptive(150.dp)
        ) {
            items(
                items = shopViewModel.products,
                itemContent = {
                    ProductItemUi(product = it, navController)
                }
            )
        }
    }
}
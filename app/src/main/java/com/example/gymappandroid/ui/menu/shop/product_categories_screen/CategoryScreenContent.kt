package com.example.gymappandroid.ui.menu.shop

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryScreenContent(shopViewModel: ShopViewModel, navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            contentPadding = PaddingValues(16.dp),
            columns = GridCells.Adaptive(150.dp)
        ) {
            items(
                items = shopViewModel.shopCategories,
                itemContent = {
                    CategoryItem(it, navController)
                }
            )
        }
    }
}
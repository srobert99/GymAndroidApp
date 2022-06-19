package com.example.gymappandroid.ui.menu.shop.cart_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymappandroid.data.models.ShoppingCartItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShoppingCartScreenContent(
    shoppingCartProducts: List<ShoppingCartItem>,
    removeItemFromShoppingList: (shoppingItemId: String) -> Unit,

    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        if (shoppingCartProducts.isEmpty()) {
            Text(
                "No items added yet",
                color = Color.Gray,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        LazyColumn(contentPadding = PaddingValues(20.dp)) {
            items(
                shoppingCartProducts,
                { shoppingListItem -> shoppingListItem.shoppingCartItemId }
            ) { item ->
                val dismissState = rememberDismissState()

                if (dismissState.isDismissed(DismissDirection.StartToEnd)) {
                    removeItemFromShoppingList(item.shoppingCartItemId)
                }

                SwipeToDismiss(state = dismissState,
                    directions = setOf(
                        DismissDirection.StartToEnd
                    ),
                    background = {
                        val alignment = Alignment.CenterStart
                        val icon = Icons.Default.Delete

                        val scale by animateFloatAsState(
                            if (dismissState.targetValue == DismissValue.Default) 2f else 3f
                        )
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp),
                            contentAlignment = alignment
                        ) {
                            Icon(
                                icon,
                                contentDescription = "",
                                modifier = Modifier.scale(scale)
                            )
                        }
                    },
                    dismissContent = {
                        CartItemUI(shoppingCartItem = item)
                    }
                )
            }
        }
        Text(
            "To remove items from the cart swipe item to the left side",
            color = Color.Gray,
            fontSize = 10.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
        )
        if (shoppingCartProducts.isNotEmpty())
            Button(
                onClick = {},
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 10.dp, bottom = 10.dp)
            ) {
                Text("Next")
            }
    }
}
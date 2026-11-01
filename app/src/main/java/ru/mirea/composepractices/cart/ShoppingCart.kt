package ru.mirea.composepractices.cart

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random
import ru.mirea.composepractices.R
import ru.mirea.composepractices.ui.theme.PracticeTheme

@Composable
fun ShoppingCartTask(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    ShoppingCart(
        modifier = modifier,
        onFreeDelivery = {
            Toast.makeText(context, R.string.free_delivery, Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
fun ShoppingCart(
    onFreeDelivery: () -> Unit,
    modifier: Modifier = Modifier,
    nextPrice: () -> Int = { Random.nextInt(0, 100) }
) {
    var cart by rememberSaveable(stateSaver = Cart.Saver) { mutableStateOf(Cart.initial()) }
    val productName = stringResource(R.string.product_name, cart.nextProductId + 1)
    ShoppingCartScreen(
        cart = cart,
        onAdd = {
            val updatedCart = cart.addProduct(name = productName, price = nextPrice())
            cart = updatedCart
            if (updatedCart.totalSum > FREE_DELIVERY_THRESHOLD) onFreeDelivery()
        },
        onRemove = { cart = cart.removeLastProduct() },
        modifier = modifier
    )
}

@Composable
fun ShoppingCartScreen(cart: Cart, onAdd: () -> Unit, onRemove: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        cart.products.forEach { product ->
            Text(text = stringResource(R.string.product_row, product.name, product.price))
        }
        Text(
            text = stringResource(R.string.cart_total, cart.totalSum),
            modifier = Modifier.testTag("cart_total"),
            style = MaterialTheme.typography.titleLarge
        )
        Button(onClick = onAdd) { Text(text = stringResource(R.string.add_product)) }
        if (cart.products.isNotEmpty()) {
            OutlinedButton(onClick = onRemove) { Text(text = stringResource(R.string.remove_product)) }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoppingCartPreview() {
    PracticeTheme { ShoppingCartScreen(cart = Cart.initial(), onAdd = {}, onRemove = {}) }
}

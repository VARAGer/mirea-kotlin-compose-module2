package ru.mirea.composepractices.cart

import androidx.compose.runtime.saveable.listSaver

const val FREE_DELIVERY_THRESHOLD = 500

data class Product(val id: Int, val name: String, val price: Int)

data class Cart(val products: List<Product>, val nextProductId: Int = 3) {
    val totalSum: Int get() = products.sumOf { it.price }

    fun addProduct(name: String, price: Int): Cart = copy(
        products = products + Product(id = nextProductId, name = name, price = price),
        nextProductId = nextProductId + 1
    )

    fun removeLastProduct(): Cart = copy(products = products.dropLast(1))

    companion object {
        fun initial(): Cart = Cart(
            products = listOf(
                Product(0, "Товар #1", 100),
                Product(1, "Товар #2", 150),
                Product(2, "Товар #3", 56)
            )
        )

        val Saver = listSaver<Cart, Any>(
            save = { cart ->
                listOf(cart.nextProductId) + cart.products.flatMap { listOf(it.id, it.name, it.price) }
            },
            restore = { values ->
                Cart(
                    products = values.drop(1).chunked(3).map {
                        Product(id = it[0] as Int, name = it[1] as String, price = it[2] as Int)
                    },
                    nextProductId = values[0] as Int
                )
            }
        )
    }
}

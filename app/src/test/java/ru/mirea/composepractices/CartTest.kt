package ru.mirea.composepractices

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.mirea.composepractices.cart.Cart

class CartTest {
    @Test
    fun initialTotal_is306AndDoesNotAccumulateWhenRead() {
        val cart = Cart.initial()
        repeat(10) { assertEquals(306, cart.totalSum) }
    }

    @Test
    fun addProduct_updatesTotalAndKeepsOriginalUnchanged() {
        val initial = Cart.initial()
        val updated = initial.addProduct("Тестовый товар", 94)
        assertEquals(400, updated.totalSum)
        assertEquals(306, initial.totalSum)
        assertEquals(4, updated.products.size)
    }

    @Test
    fun removeLastProduct_removesOnlyLastItem() {
        val updated = Cart.initial().removeLastProduct()
        assertEquals(250, updated.totalSum)
        assertEquals(listOf(0, 1), updated.products.map { it.id })
    }

    @Test
    fun removeFromEmptyCart_isSafe() {
        assertTrue(Cart(emptyList()).removeLastProduct().products.isEmpty())
    }

    @Test
    fun addAfterRemove_usesNextIdentifier() {
        val initial = Cart.initial().addProduct("Первый", 20)
        val updated = initial.removeLastProduct().addProduct("Второй", 30)
        assertEquals(4, updated.products.last().id)
    }
}

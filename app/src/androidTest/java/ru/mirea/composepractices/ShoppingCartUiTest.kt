package ru.mirea.composepractices

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import ru.mirea.composepractices.cart.ShoppingCart
import ru.mirea.composepractices.ui.theme.PracticeTheme

class ShoppingCartUiTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun removeButton_disappearsWhenCartBecomesEmpty() {
        composeRule.setContent { PracticeTheme { ShoppingCart(onFreeDelivery = {}) } }
        repeat(3) { composeRule.onNodeWithText("Удалить товар").performClick() }
        composeRule.onNodeWithText("Удалить товар").assertDoesNotExist()
        composeRule.onNodeWithTag("cart_total").assertTextEquals("Товаров на сумму: 0 рублей")
    }

    @Test
    fun deliveryNotification_usesStrictlyGreaterThan500AndFollowsAddEvent() {
        var notifications = 0
        composeRule.setContent {
            PracticeTheme { ShoppingCart(onFreeDelivery = { notifications++ }, nextPrice = { 97 }) }
        }
        repeat(2) { composeRule.onNodeWithText("Добавить товар").performClick() }
        composeRule.onNodeWithTag("cart_total").assertTextEquals("Товаров на сумму: 500 рублей")
        composeRule.runOnIdle { assertEquals(0, notifications) }
        composeRule.onNodeWithText("Добавить товар").performClick()
        composeRule.onNodeWithTag("cart_total").assertTextEquals("Товаров на сумму: 597 рублей")
        composeRule.runOnIdle { assertEquals(1, notifications) }
        composeRule.onNodeWithText("Удалить товар").performClick()
        composeRule.runOnIdle { assertEquals(1, notifications) }
    }

    @Test
    fun cart_survivesActivityStateRestoration() {
        val restorationTester = StateRestorationTester(composeRule)
        restorationTester.setContent {
            PracticeTheme { ShoppingCart(onFreeDelivery = {}, nextPrice = { 50 }) }
        }
        composeRule.onNodeWithText("Добавить товар").performClick()
        restorationTester.emulateSavedInstanceStateRestore()
        composeRule.onNodeWithTag("cart_total").assertTextEquals("Товаров на сумму: 356 рублей")
    }
}

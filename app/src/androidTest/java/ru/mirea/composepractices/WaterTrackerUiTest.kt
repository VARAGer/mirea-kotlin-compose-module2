package ru.mirea.composepractices

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import ru.mirea.composepractices.ui.theme.PracticeTheme
import ru.mirea.composepractices.water.WaterTracker

class WaterTrackerUiTest {
    @get:Rule
    val composeRule = createComposeRule()

    private fun launchTracker() {
        composeRule.setContent { PracticeTheme { WaterTracker() } }
    }

    private fun addGlasses(count: Int) {
        repeat(count) { composeRule.onNodeWithText("+250 мл").performClick() }
    }

    private fun finishDay() {
        composeRule.onNodeWithText("Завершить день").performClick()
    }

    private fun assertStreak(days: Int) {
        composeRule.onNodeWithTag("water_streak")
            .assertIsDisplayed().assertTextEquals("Дней подряд с 1500 мл и больше: $days")
    }

    @Test
    fun addButton_isVisibleAndAdds250EachTime() {
        launchTracker()
        composeRule.onNodeWithText("+250 мл").assertIsDisplayed().assertIsEnabled()
        composeRule.onNodeWithTag("water_volume").assertTextEquals("0 мл")
        addGlasses(1)
        composeRule.onNodeWithTag("water_volume").assertTextEquals("250 мл")
        addGlasses(1)
        composeRule.onNodeWithTag("water_volume").assertTextEquals("500 мл")
    }

    @Test
    fun finishDayButton_isVisibleAndResetsVolume() {
        launchTracker()
        composeRule.onNodeWithText("Завершить день").assertIsDisplayed().assertIsEnabled()
        addGlasses(2)
        finishDay()
        composeRule.onNodeWithTag("water_volume").assertTextEquals("0 мл")
        assertStreak(0)
    }

    @Test
    fun streak_countsSuccessfulDaysAndResetsAfterMissedGoal() {
        launchTracker()
        assertStreak(0)
        addGlasses(6)
        finishDay()
        assertStreak(1)
        addGlasses(7)
        finishDay()
        assertStreak(2)
        addGlasses(5)
        finishDay()
        assertStreak(0)
    }

    @Test
    fun finishEmptyDay_resetsExistingStreak() {
        launchTracker()
        addGlasses(6)
        finishDay()
        assertStreak(1)
        finishDay()
        assertStreak(0)
    }

    @Test
    fun state_survivesActivityStateRestoration() {
        val restorationTester = StateRestorationTester(composeRule)
        restorationTester.setContent { PracticeTheme { WaterTracker() } }
        addGlasses(6)
        finishDay()
        addGlasses(1)
        restorationTester.emulateSavedInstanceStateRestore()
        composeRule.onNodeWithTag("water_volume").assertTextEquals("250 мл")
        assertStreak(1)
    }
}

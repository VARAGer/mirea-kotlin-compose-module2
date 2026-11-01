package ru.mirea.composepractices

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.mirea.composepractices.water.WaterProgress

class WaterProgressTest {
    @Test
    fun addGlass_increasesVolumeBy250() {
        assertEquals(500, WaterProgress(volumeMl = 250).addGlass().volumeMl)
    }

    @Test
    fun finishDay_atGoal_increasesStreakAndResetsVolume() {
        assertEquals(WaterProgress(0, 3), WaterProgress(1500, 2).finishDay())
    }

    @Test
    fun finishDay_aboveGoal_increasesStreak() {
        assertEquals(WaterProgress(0, 2), WaterProgress(1750, 1).finishDay())
    }

    @Test
    fun finishDay_belowGoal_resetsStreak() {
        assertEquals(WaterProgress(), WaterProgress(1250, 4).finishDay())
    }

    @Test
    fun finishDay_withoutWater_resetsStreak() {
        assertEquals(WaterProgress(), WaterProgress(0, 3).finishDay())
    }
}

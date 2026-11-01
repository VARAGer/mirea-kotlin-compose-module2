package ru.mirea.composepractices.water

import androidx.compose.runtime.saveable.listSaver

const val DAILY_GOAL_ML = 1500
const val GLASS_VOLUME_ML = 250

data class WaterProgress(val volumeMl: Int = 0, val streakDays: Int = 0) {
    fun addGlass(): WaterProgress = copy(volumeMl = volumeMl + GLASS_VOLUME_ML)

    fun finishDay(): WaterProgress = WaterProgress(
        volumeMl = 0,
        streakDays = if (volumeMl >= DAILY_GOAL_ML) streakDays + 1 else 0
    )

    companion object {
        val Saver = listSaver<WaterProgress, Int>(
            save = { listOf(it.volumeMl, it.streakDays) },
            restore = { WaterProgress(volumeMl = it[0], streakDays = it[1]) }
        )
    }
}

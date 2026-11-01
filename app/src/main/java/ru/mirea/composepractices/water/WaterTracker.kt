package ru.mirea.composepractices.water

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mirea.composepractices.R
import ru.mirea.composepractices.ui.theme.PracticeTheme

@Composable
fun WaterTracker(modifier: Modifier = Modifier) {
    var progress by rememberSaveable(stateSaver = WaterProgress.Saver) { mutableStateOf(WaterProgress()) }
    WaterTrackerScreen(
        progress = progress,
        onAddGlass = { progress = progress.addGlass() },
        onFinishDay = { progress = progress.finishDay() },
        modifier = modifier
    )
}

@Composable
fun WaterTrackerScreen(
    progress: WaterProgress,
    onAddGlass: () -> Unit,
    onFinishDay: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = stringResource(R.string.water_title),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(R.string.water_volume, progress.volumeMl),
            modifier = Modifier.testTag("water_volume"),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary
        )
        Button(onClick = onAddGlass) {
            Text(text = stringResource(R.string.add_water, GLASS_VOLUME_ML))
        }
        OutlinedButton(onClick = onFinishDay) {
            Text(text = stringResource(R.string.finish_day))
        }
        Text(
            text = stringResource(R.string.water_streak, DAILY_GOAL_ML, progress.streakDays),
            modifier = Modifier.testTag("water_streak")
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WaterTrackerPreview() {
    PracticeTheme {
        WaterTrackerScreen(progress = WaterProgress(1500, 2), onAddGlass = {}, onFinishDay = {})
    }
}

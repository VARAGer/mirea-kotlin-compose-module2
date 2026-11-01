package ru.mirea.composepractices.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.mirea.composepractices.BuildConfig
import ru.mirea.composepractices.R
import ru.mirea.composepractices.cart.ShoppingCartTask
import ru.mirea.composepractices.tasks.CircleTask
import ru.mirea.composepractices.tasks.ContactsTask
import ru.mirea.composepractices.tasks.Hello
import ru.mirea.composepractices.tasks.InitialsTask
import ru.mirea.composepractices.tasks.StyledButton
import ru.mirea.composepractices.tasks.TextTask
import ru.mirea.composepractices.water.WaterTracker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeApp(modifier: Modifier = Modifier, initialTask: Int = 0) {
    var selectedTask by rememberSaveable { mutableIntStateOf(initialTask.coerceIn(0, 10)) }
    BackHandler(enabled = selectedTask != 0) { selectedTask = 0 }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (selectedTask == 0) {
                            stringResource(R.string.module_title)
                        } else {
                            stringResource(R.string.task_title, selectedTask)
                        }
                    )
                },
                navigationIcon = {
                    if (selectedTask != 0) {
                        TextButton(onClick = { selectedTask = 0 }) { Text(stringResource(R.string.back)) }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
                .verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (selectedTask) {
                0 -> (1..10).forEach { task ->
                    Button(onClick = { selectedTask = task }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = stringResource(R.string.task_title, task))
                    }
                }
                1 -> Hello(name = BuildConfig.GREETING_NAME)
                2 -> Hello(name = "Android")
                3 -> TextTask()
                4 -> StyledButton()
                5 -> InitialsTask()
                6 -> CircleTask()
                7 -> ContactsTask()
                8 -> WaterTracker()
                9 -> Text(text = stringResource(R.string.water_test_hint))
                10 -> ShoppingCartTask()
            }
        }
    }
}

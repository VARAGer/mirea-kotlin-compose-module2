package ru.mirea.composepractices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.mirea.composepractices.ui.PracticeApp
import ru.mirea.composepractices.ui.theme.PracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val initialTask = intent.getIntExtra("task", 0)
        setContent {
            PracticeTheme {
                PracticeApp(initialTask = initialTask)
            }
        }
    }
}

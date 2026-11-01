package ru.mirea.composepractices.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PracticeColors = lightColorScheme(
    primary = Color(0xFF415F91),
    secondary = Color(0xFF565F71),
    background = Color(0xFFF9F9FF),
    surface = Color(0xFFF9F9FF)
)

@Composable
fun PracticeTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = PracticeColors, content = content)
}

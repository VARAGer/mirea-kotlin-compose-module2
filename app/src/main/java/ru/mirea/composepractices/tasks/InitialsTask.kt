package ru.mirea.composepractices.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mirea.composepractices.R
import ru.mirea.composepractices.ui.theme.PracticeTheme

@Composable
fun InitialsTask(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth().height(100.dp).background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        InitialsAvatar(initials = stringResource(R.string.initials))
    }
}

@Composable
fun InitialsAvatar(initials: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.padding(vertical = 16.dp).fillMaxHeight().aspectRatio(1f)
            .clip(CircleShape).background(Color(0xFFFF5722)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = initials, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
private fun InitialsTaskPreview() {
    PracticeTheme { InitialsTask() }
}

package ru.mirea.composepractices.tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mirea.composepractices.R
import ru.mirea.composepractices.ui.theme.PracticeTheme

@Composable
fun CircleTask(modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = stringResource(R.string.circle_original))
        TopEndCircle()
        Text(text = stringResource(R.string.circle_stretched))
        StretchedCircle()
    }
}

@Composable
fun TopEndCircle(modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(width = 240.dp, height = 120.dp).background(Color.Black)) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = stringResource(R.string.circle_original),
            modifier = Modifier.size(48.dp).align(Alignment.TopEnd)
        )
    }
}

@Composable
fun StretchedCircle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.size(width = 240.dp, height = 120.dp).background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = stringResource(R.string.circle_stretched),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color(0xFF800080))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CircleTaskPreview() {
    PracticeTheme { CircleTask() }
}

package ru.mirea.composepractices.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mirea.composepractices.R
import ru.mirea.composepractices.ui.theme.PracticeTheme

@Composable
fun Hello(name: String?, modifier: Modifier = Modifier) {
    val message = name?.let { stringResource(R.string.hello, it) }
        ?: stringResource(R.string.name_not_set)
    Text(text = message, modifier = modifier)
}

@Preview(name = "portrait", showSystemUi = true, widthDp = 360, heightDp = 640)
@Composable
private fun HelloPortraitPreview() {
    PracticeTheme {
        Hello(name = "Android", modifier = Modifier.padding(16.dp))
    }
}

@Preview(name = "landscape", showSystemUi = true, widthDp = 640, heightDp = 360)
@Composable
private fun HelloLandscapePreview() {
    PracticeTheme {
        Hello(name = "Android", modifier = Modifier.padding(16.dp))
    }
}

@Preview(
    name = "round",
    showSystemUi = true,
    device = "spec:width=200dp,height=200dp,dpi=320,isRound=true,chinSize=0dp",
    showBackground = true,
    backgroundColor = 0xFFFFFF00
)
@Composable
private fun HelloRoundPreview() {
    PracticeTheme {
        Box(modifier = Modifier.fillMaxSize().background(Color.Yellow)) {
            Hello(name = "Android", modifier = Modifier.padding(24.dp))
        }
    }
}

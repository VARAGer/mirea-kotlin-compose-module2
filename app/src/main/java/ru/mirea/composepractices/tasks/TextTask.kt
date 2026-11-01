package ru.mirea.composepractices.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.mirea.composepractices.R
import ru.mirea.composepractices.ui.theme.PracticeTheme

@Composable
fun TextTask(modifier: Modifier = Modifier) {
    val description = stringResource(R.string.compose_description)
    val firstLineOffset = with(LocalDensity.current) { 48.sp.toDp() }
    Column(modifier = modifier) {
        Text(
            text = description,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Green,
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center
        )
        Text(
            text = description,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(firstLineOffset))
        Text(
            text = description,
            modifier = Modifier.fillMaxWidth().background(Color.Green),
            color = Color.Black,
            fontSize = 24.sp,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextTaskPreview() {
    PracticeTheme { TextTask() }
}

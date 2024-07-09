package com.nmartindev.fortnite.ui.composables

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nmartindev.fortnite.ui.theme.FortniteTheme


@Composable
fun WaitIndicator(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
    )
}

@Composable
@Preview(showBackground = true)
fun PreviewWaitIndicator() {
    FortniteTheme {
        CircularProgressIndicator()
    }
}
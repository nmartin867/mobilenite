package com.nmartindev.fortnite.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun FortniteTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = ColorPalette, typography = Typography, content = content)
}
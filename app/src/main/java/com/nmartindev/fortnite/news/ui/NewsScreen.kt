package com.nmartindev.fortnite.news.ui

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nmartindev.fortnite.news.viewmodel.NewsViewModel
import com.nmartindev.fortnite.ui.theme.FortniteTheme
import java.lang.reflect.Modifier

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = viewModel()
) {
    Text(text = "Fuck")
}
package com.nmartindev.fortnite

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.nmartindev.fortnite.news.ui.NewsScreen
import com.nmartindev.fortnite.news.viewmodel.NewsViewModel

@Composable
fun FortniteNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = News.route,
        modifier = modifier
    ) {
        composable(route = News.route) {
            val viewModel = hiltViewModel<NewsViewModel>()
            NewsScreen(viewModel)
        }
    }
}
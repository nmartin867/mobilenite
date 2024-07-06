package com.nmartindev.fortnite

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed interface FortniteDestination {
    val icon: ImageVector
    val route: String
}

data object News: FortniteDestination {
    override val icon = Icons.Filled.Notifications
    override val route = "news"
}

val fortniteTabRowScreens = listOf(News)
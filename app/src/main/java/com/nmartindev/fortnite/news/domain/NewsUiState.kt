package com.nmartindev.fortnite.news.domain

import com.nmartindev.fortnite.domain.FortniteGameType
import com.nmartindev.fortnite.domain.NewsItem

data class NewsUiState(
    val newsType: FortniteGameType = FortniteGameType.BATTLE_ROYAL,
    val loading: Boolean = false,
    val newsItems: List<NewsItem> = emptyList(),
    val error: Throwable? = null
)


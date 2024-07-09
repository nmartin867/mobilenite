package com.nmartindev.fortnite.news.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nmartindev.fortnite.domain.NewsItem
import com.nmartindev.fortnite.news.viewmodel.NewsViewModel

@Composable
fun NewsScreen(
    viewModel: NewsViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(uiState.loading) {
            CircularProgressIndicator(modifier = Modifier.width(64.dp))
        }

        if(uiState.newsItems.isNotEmpty()) {
            FortniteNewsList(newsItems = uiState.newsItems)
        }
    }
}

@Composable
fun FortniteNewsList(
    newsItems: List<NewsItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn( modifier = modifier) {
        items(
            items = newsItems,
            key = { item -> item.id }
        ) { item ->
            FortniteNewsItem(item = item)
        }
    }
}

@Composable
fun FortniteNewsItem(
    item: NewsItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = item.title
        )
    }
}


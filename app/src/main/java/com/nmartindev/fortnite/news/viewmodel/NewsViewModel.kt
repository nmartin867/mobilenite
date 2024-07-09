package com.nmartindev.fortnite.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nmartindev.fortnite.di.IoDispatcher
import com.nmartindev.fortnite.news.data.IFortniteNewsRepository
import com.nmartindev.fortnite.news.domain.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: IFortniteNewsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    init { getBattleRoyalNews() }

    fun getBattleRoyalNews() = viewModelScope.launch(ioDispatcher) {
        _uiState.value = NewsUiState(loading = true)
        try {
            val items = newsRepository.getNews()
            _uiState.value = NewsUiState(loading = false, newsItems = items)
        } catch (e: Throwable) {
            _uiState.value = NewsUiState(error = e)
        }
    }
}
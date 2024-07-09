package com.nmartindev.fortnite.news.data

import com.nmartindev.fortnite.data.FortniteService
import com.nmartindev.fortnite.domain.Error
import com.nmartindev.fortnite.domain.FortniteGameType
import com.nmartindev.fortnite.domain.FortniteNewsResponse
import com.nmartindev.fortnite.domain.NetworkException
import com.nmartindev.fortnite.domain.NewsItem
import com.nmartindev.fortnite.domain.Success
import com.nmartindev.fortnite.domain.UnknownError
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface IFortniteNewsRepository {
    suspend fun getNews(gameType: FortniteGameType = FortniteGameType.BATTLE_ROYAL): List<NewsItem>
}

class FortniteNewsRepository @Inject constructor (private val service: FortniteService) :
    IFortniteNewsRepository {
    override suspend fun getNews(gameType: FortniteGameType): List<NewsItem> {
        return when(val response = service.getNews(gameType.type)){
           is Error -> throw Throwable(response.message)
           is NetworkException -> throw response.e
           is Success -> response.data.news
           is UnknownError -> throw response.e
       }
    }
}
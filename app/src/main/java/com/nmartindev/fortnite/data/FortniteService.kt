package com.nmartindev.fortnite.data

import com.nmartindev.fortnite.domain.FortniteNewsResponse
import com.nmartindev.fortnite.domain.NetworkResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FortniteService {
    @GET("v1/news")
    suspend fun getNews(@Query("type") type: String): NetworkResult<FortniteNewsResponse>
}
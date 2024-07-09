package com.nmartindev.fortnite.domain


import com.squareup.moshi.JsonClass
import org.joda.time.DateTime

@JsonClass(generateAdapter = true)
data class FortniteNewsResponse(
    val lang: String,
    val news: List<NewsItem>,
    val result: Boolean,
    val show: Int,
    val type: String
)

@JsonClass(generateAdapter = true)
data class NewsItem(
    val adspace: String,
    val body: String,
    val date: DateTime,
    val id: String,
    val image: String,
    val live: Boolean,
    val tabTitle: String,
    val title: String,
    val video: String?
)
package com.nmartindev.fortnite

import com.nmartindev.fortnite.data.moshi.JodaDateTimeAdapter
import com.nmartindev.fortnite.domain.FortniteNewsResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.joda.time.DateTime
import org.junit.Test

import org.junit.Assert.*
import org.junit.BeforeClass
import java.io.BufferedReader
import java.io.InputStreamReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class MoshiNewsUnitTest {

    @Test
    fun news_items_deserialized() {
        val expectedCount = 25
        val actualCount = newsResponse.news.count()

        assertTrue(
            "Expected $expectedCount news items. Found $actualCount",
            actualCount == expectedCount
        )
    }

    @Test
    fun news_items_date_deserialized() {
        val actualDate = newsResponse.news.first().date
        assertTrue("", actualDate.year == 2023)
    }

    companion object {
        const val RESPONSE_RESOURCE = "br_news_response.json"

        private val moshi = Moshi.Builder()
            .add(JodaDateTimeAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()

        private lateinit var jsonString: String

        private lateinit var newsResponse: FortniteNewsResponse


        @JvmStatic
        @BeforeClass
        fun beforeAllTests(): Unit {
            readTestResource(RESPONSE_RESOURCE)
            deserializeNewsResponse()

        }

        private fun readTestResource(fileName: String) {
             MoshiNewsUnitTest::class.java.classLoader?.let { classLoader ->
                classLoader.getResourceAsStream(fileName)?.let { inputStream ->
                    inputStream.use {
                       jsonString = BufferedReader(InputStreamReader(it)).readText()
                    }
                }
            } ?: kotlin.run {
                throw Throwable()
            }
        }

        @OptIn(ExperimentalStdlibApi::class)
        private fun deserializeNewsResponse() {
            val jsonAdapter = moshi.adapter<FortniteNewsResponse>()
            newsResponse = jsonAdapter.fromJson(jsonString)!!
        }
    }
}
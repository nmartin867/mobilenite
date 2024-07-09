package com.nmartindev.fortnite.news.viewmodel

import com.nmartindev.fortnite.MoshiNewsUnitTest
import com.nmartindev.fortnite.MoshiNewsUnitTest.Companion
import com.nmartindev.fortnite.data.FortniteService
import com.nmartindev.fortnite.data.moshi.JodaDateTimeAdapter
import com.nmartindev.fortnite.domain.FortniteNewsResponse
import com.nmartindev.fortnite.domain.NetworkResult
import com.nmartindev.fortnite.domain.NewsItem
import com.nmartindev.fortnite.domain.Success
import com.nmartindev.fortnite.news.data.FortniteNewsRepository
import com.nmartindev.fortnite.news.data.IFortniteNewsRepository
import com.nmartindev.fortnite.news.domain.NewsUiState
import com.nmartindev.test.helpers.IMoshiTestClass
import com.nmartindev.test.helpers.IResourceTestClass
import com.nmartindev.test.helpers.MoshiTestClass
import com.nmartindev.test.helpers.ResourceTestClass
import com.nmartindev.test.rules.CoroutineTestRule
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Rule

import org.junit.Test
import retrofit2.Response
import retrofit2.mock.Calls
import java.io.BufferedReader
import java.io.InputStreamReader

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModelTest {
    @get:Rule
    val mainDispatcherRule = CoroutineTestRule()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var newsServiceMock: FortniteService

    @Test
    fun `uistate should not be loading on init`() = runTest {
        val repo = FortniteNewsRepository(newsServiceMock)
        val viewModel = NewsViewModel(
            repo,
            mainDispatcherRule.dispatcher
        )
        advanceUntilIdle()
        val loadingState = viewModel.uiState.value.loading
        assertFalse("Loading state should be false", loadingState)
    }

    companion object {
        val moshi by lazy {
            Moshi.Builder()
                .add(JodaDateTimeAdapter())
                .addLast(KotlinJsonAdapterFactory())
                .build()
        }

        val newsResponseJson: String by lazy {
            BufferedReader(InputStreamReader(
                this::class.java.classLoader.getResourceAsStream("br_news_response.json")
            )).readText()
        }

        @OptIn(ExperimentalStdlibApi::class)
        fun getNewsResponse(): FortniteNewsResponse {
           val jsonAdapter = moshi.adapter<FortniteNewsResponse>()
            return jsonAdapter.fromJson(newsResponseJson)!!
        }
    }
}
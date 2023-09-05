package com.murerwa.animeapp.features.shows.data.api

import com.google.gson.Gson
import com.murerwa.animeapp.features.shows.data.models.AnimeListResponse
import com.murerwa.animeapp.features.shows.fixtures.tPagination
import com.murerwa.animeapp.features.shows.fixtures.tShowModel
import com.murerwa.animeapp.utils.BaseUnitTest
import com.murerwa.animeapp.utils.RetrofitHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

@OptIn(ExperimentalCoroutinesApi::class)
class AnimeShowsApiClientShould: BaseUnitTest() {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var testAnimeShowsApiClient: AnimeShowsApiClient

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        testAnimeShowsApiClient = RetrofitHelper
            .testShowsApiInstance(mockWebServer.url("/").toString())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun returnCharactersIfGetShowsSuccess() = runTest {
        val expected = AnimeListResponse(
            shows = listOf(tShowModel),
            pagination = tPagination
        )
        setSuccessWebserverResponse(expected)

        val actualResponse = testAnimeShowsApiClient.getShows()

        assertEquals(expected, actualResponse)
    }

    @Test
    fun throwExceptionIfGetShowsFailed() = runTest {
        setFailureWebserverResponse("Bad Request")

        try {
            testAnimeShowsApiClient.getShows()
        } catch (e: Exception) {
            assertEquals(e.message, "HTTP 400 Client Error")
        }
    }

    private fun setSuccessWebserverResponse(expected: Any) {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(expected))
        mockWebServer.enqueue(response)
    }

    private fun setFailureWebserverResponse(expected: Any) {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(Gson().toJson(expected))
        mockWebServer.enqueue(response)
    }
}
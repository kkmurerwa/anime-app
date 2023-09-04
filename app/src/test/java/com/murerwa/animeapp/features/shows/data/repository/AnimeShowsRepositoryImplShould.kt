package com.murerwa.animeapp.features.shows.data.repository

import com.murerwa.animeapp.core.network.NetworkResult
import com.murerwa.animeapp.features.shows.data.api.AnimeShowsApiClient
import com.murerwa.animeapp.features.shows.data.repositories.AnimeShowsRepositoryImpl
import com.murerwa.animeapp.features.shows.domain.models.AnimeListResponse
import com.murerwa.animeapp.features.shows.fixtures.tPagination
import com.murerwa.animeapp.features.shows.fixtures.tShow
import com.murerwa.animeapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.MockitoKotlinException
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AnimeShowsRepositoryImplShould: BaseUnitTest() {
    private val apiClient = mock<AnimeShowsApiClient>()
    private val repository = AnimeShowsRepositoryImpl(apiClient)
    private val fakeResponse = AnimeListResponse(
        shows = listOf(tShow),
        pagination = tPagination
    )

    @Test
    fun invokeApiClientOnGetShowsCalled() = runTest {
        whenever(apiClient.getShows()).thenReturn(fakeResponse)

        repository.getAnimeShows()

        verify(apiClient, times(1)).getShows()
    }

    @Test
    fun getShowsFromRepositoryAsNetworkResultOfTypeSuccessIfSuccessful() = runTest {
        whenever(apiClient.getShows()).thenReturn(fakeResponse)

        val actual = repository.getAnimeShows()

        assertEquals(actual::class, NetworkResult.Success::class)
    }

    @Test
    fun getShowsFromRepositoryIfSuccessful() = runTest {
        whenever(apiClient.getShows()).thenReturn(fakeResponse)

        val actual = repository.getAnimeShows()

        assertEquals(NetworkResult.Success(fakeResponse), actual)
    }

    @Test
    fun getShowsFromRepositoryAsNetworkResultOfTypeFailureIfFailed() = runTest {
        whenever(apiClient.getShows()).thenThrow(MockitoKotlinException("", Exception("")))

        val actual = repository.getAnimeShows()

        assertEquals(actual::class, NetworkResult.Failure::class)
    }
}

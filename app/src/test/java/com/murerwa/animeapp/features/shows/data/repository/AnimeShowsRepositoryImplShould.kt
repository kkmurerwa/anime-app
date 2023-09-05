package com.murerwa.animeapp.features.shows.data.repository

import com.murerwa.animeapp.features.shows.data.datasources.LocalShowsDataSource
import com.murerwa.animeapp.features.shows.data.datasources.RemoteShowsDataSource
import com.murerwa.animeapp.features.shows.data.models.AnimeListResponse
import com.murerwa.animeapp.features.shows.data.repositories.AnimeShowsRepositoryImpl
import com.murerwa.animeapp.features.shows.fixtures.tPagination
import com.murerwa.animeapp.features.shows.fixtures.tShowModel
import com.murerwa.animeapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class AnimeShowsRepositoryImplShould: BaseUnitTest() {
    private val remoteShowsDataSource = mock<RemoteShowsDataSource>()
    private val localShowsDataSource = mock<LocalShowsDataSource>()
    private val repository = AnimeShowsRepositoryImpl(
        localShowsDataSource = localShowsDataSource,
        remoteShowsDataSource = remoteShowsDataSource
    )
    private val fakeResponse = AnimeListResponse(
        shows = listOf(tShowModel),
        pagination = tPagination
    )
//
//    @Test
//    fun invokeApiClientOnGetShowsCalled() = runTest {
//        whenever(remoteShowsDataSource.fetchShows())
//            .thenReturn(DataSourceState.Success(fakeResponse))
//
//        repository.getAnimeShows()
//
//        verify(remoteShowsDataSource, times(1)).fetchShows()
//    }
//
//    @Test
//    fun getShowsFromRepositoryAsNetworkResultOfTypeSuccessIfSuccessful() = runTest {
//        whenever(apiClient.getShows()).thenReturn(fakeResponse)
//
//        val actual = repository.getAnimeShows()
//
//        assertEquals(actual::class, DataSourceState.Success::class)
//    }
//
//    @Test
//    fun getShowsFromRepositoryIfSuccessful() = runTest {
//        whenever(apiClient.getShows()).thenReturn(fakeResponse)
//
//        val actual = repository.getAnimeShows()
//
//        assertEquals(DataSourceState.Success(fakeResponse), actual)
//    }
//
//    @Test
//    fun getShowsFromRepositoryAsNetworkResultOfTypeFailureIfFailed() = runTest {
//        whenever(apiClient.getShows()).thenThrow(MockitoKotlinException("", Exception("")))
//
//        val actual = repository.getAnimeShows()
//
//        assertEquals(actual::class, DataSourceState.Failure::class)
//    }
}

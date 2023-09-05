package com.murerwa.animeapp.features.shows.domain.usecases

import com.murerwa.animeapp.core.network.DataSourceState
import com.murerwa.animeapp.core.network.UIState
import com.murerwa.animeapp.features.shows.data.repositories.AnimeShowsRepositoryImpl
import com.murerwa.animeapp.features.shows.fixtures.tExpectedNetworkResultFailure
import com.murerwa.animeapp.features.shows.fixtures.tPagination
import com.murerwa.animeapp.features.shows.fixtures.tShow
import com.murerwa.animeapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetAnimeShowsUseCaseShould: BaseUnitTest() {
    private val mockRepository = mock<AnimeShowsRepositoryImpl>()

    private val getAnimeShowsUseCase = GetAnimeShowsUseCaseImpl(mockRepository)

    private val fakeResponse = AnimeListResponse(
        shows = listOf(tShow),
        pagination = tPagination
    )

    @Test
    fun invokeAnimeShowsRepositoryOnExecuteCalled() = runTest {
        whenever(mockRepository.getAnimeShows())
            .thenReturn(DataSourceState.Success(fakeResponse))

        getAnimeShowsUseCase.execute()

        verify(mockRepository, times(1)).getAnimeShows()
    }

    @Test
    fun getShowsFromRepositoryAsUIStateOfTypeSuccessIfSuccessful() = runTest {
        whenever(mockRepository.getAnimeShows())
            .thenReturn(DataSourceState.Success(fakeResponse))

        val actual = getAnimeShowsUseCase.execute()

        assertEquals(actual::class, UIState.Success::class)
    }

    @Test
    fun getListOfShowsFromRepositoryIfSuccessful() = runTest {
        whenever(mockRepository.getAnimeShows())
            .thenReturn(DataSourceState.Success(fakeResponse))

        val actual = getAnimeShowsUseCase.execute()

        assertEquals(UIState.Success(listOf(tShow)), actual)
    }

    @Test
    fun getErrorFromRepositoryAsUIStateOfTypeErrorIfFailed() = runTest {
        whenever(mockRepository.getAnimeShows())
            .thenReturn(tExpectedNetworkResultFailure)

        val actual = getAnimeShowsUseCase.execute()

        assertEquals(actual::class, UIState.Error::class)
    }
}

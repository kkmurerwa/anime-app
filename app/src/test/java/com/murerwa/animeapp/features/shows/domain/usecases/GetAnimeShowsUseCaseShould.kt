package com.murerwa.animeapp.features.shows.domain.usecases

import com.murerwa.animeapp.features.shows.data.models.AnimeListResponse
import com.murerwa.animeapp.features.shows.data.repositories.AnimeShowsRepositoryImpl
import com.murerwa.animeapp.features.shows.fixtures.tPagination
import com.murerwa.animeapp.features.shows.fixtures.tShowModel
import com.murerwa.animeapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class GetAnimeShowsUseCaseShould: BaseUnitTest() {
    private val mockRepository = mock<AnimeShowsRepositoryImpl>()

    private val getAnimeShowsUseCase = GetAnimeShowsUseCaseImpl(mockRepository)

    private val fakeResponse = AnimeListResponse(
        shows = listOf(tShowModel),
        pagination = tPagination
    )

//    @Test
//    fun invokeAnimeShowsRepositoryOnExecuteCalled() = runTest {
//        whenever(mockRepository.getAnimeShows())
//            .thenReturn(DataSourceState.Success(fakeResponse))
//
//        getAnimeShowsUseCase.execute()
//
//        verify(mockRepository, times(1)).getAnimeShows()
//    }
//
//    @Test
//    fun getShowsFromRepositoryAsUIStateOfTypeSuccessIfSuccessful() = runTest {
//        whenever(mockRepository.getAnimeShows())
//            .thenReturn(DataSourceState.Success(fakeResponse))
//
//        val actual = getAnimeShowsUseCase.execute()
//
//        assertEquals(actual::class, UIState.Success::class)
//    }
//
//    @Test
//    fun getListOfShowsFromRepositoryIfSuccessful() = runTest {
//        whenever(mockRepository.getAnimeShows())
//            .thenReturn(DataSourceState.Success(fakeResponse))
//
//        val actual = getAnimeShowsUseCase.execute()
//
//        assertEquals(UIState.Success(listOf(tShowModel)), actual)
//    }
//
//    @Test
//    fun getErrorFromRepositoryAsUIStateOfTypeErrorIfFailed() = runTest {
//        whenever(mockRepository.getAnimeShows())
//            .thenReturn(tExpectedNetworkResultFailure)
//
//        val actual = getAnimeShowsUseCase.execute(false)
//
//        assertEquals(actual::class, UIState.Error::class)
//    }
}

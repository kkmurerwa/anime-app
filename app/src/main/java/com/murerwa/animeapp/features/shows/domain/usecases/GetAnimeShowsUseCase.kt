package com.murerwa.animeapp.features.shows.domain.usecases

import com.murerwa.animeapp.core.network.NetworkResult
import com.murerwa.animeapp.core.network.UIState
import com.murerwa.animeapp.features.shows.domain.models.Show
import com.murerwa.animeapp.features.shows.domain.repositories.AnimeShowsRepository
import com.murerwa.rickandmortytesting.core.utils.readError
import javax.inject.Inject

interface GetAnimeShowsUseCase {
    suspend fun execute(): UIState<List<Show>>
}

class GetAnimeShowsUseCaseImpl @Inject constructor(
    private val animeShowsRepository: AnimeShowsRepository
): GetAnimeShowsUseCase {
    override suspend fun execute(): UIState<List<Show>> {
        return when (val showsResponse = animeShowsRepository.getAnimeShows()) {
            is NetworkResult.Failure -> UIState.Error(
                errorMessage = showsResponse.errorBody.readError(),
                isNetworkError = showsResponse.isNetworkError
            )
            is NetworkResult.Success -> UIState.Success(showsResponse.value.shows)
        }
    }
}

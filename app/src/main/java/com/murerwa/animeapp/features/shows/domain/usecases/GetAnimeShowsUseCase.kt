package com.murerwa.animeapp.features.shows.domain.usecases

import com.murerwa.animeapp.core.network.DataSourceState
import com.murerwa.animeapp.core.network.UIState
import com.murerwa.animeapp.features.shows.domain.entities.Show
import com.murerwa.animeapp.features.shows.domain.repositories.AnimeShowsRepository
import javax.inject.Inject

/** Abstraction for easier testing and clean architecture */
interface GetAnimeShowsUseCase {
    suspend fun execute(refresh: Boolean): UIState<List<Show>>
}

/** Implementation of the abstract class [GetAnimeShowsUseCase] */
class GetAnimeShowsUseCaseImpl @Inject constructor(
    private val animeShowsRepository: AnimeShowsRepository
): GetAnimeShowsUseCase {
    override suspend fun execute(refresh: Boolean): UIState<List<Show>> {
        return when (val showsResponse = if (refresh) animeShowsRepository.refreshShows()
        else animeShowsRepository.getAnimeShows()
        ) {
            is DataSourceState.Success -> UIState.Success(showsResponse.value)
            is DataSourceState.Failure -> UIState.Error(
                errorMessage = showsResponse.errorBody,
                isNetworkError = showsResponse.isNetworkError
            )
        }
    }
}

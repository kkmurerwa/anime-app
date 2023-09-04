package com.murerwa.animeapp.features.shows.domain.repositories

import com.murerwa.animeapp.core.network.NetworkResult
import com.murerwa.animeapp.core.utils.AppConstants
import com.murerwa.animeapp.features.shows.domain.models.AnimeListResponse

/** An interface for anime operations */
interface AnimeShowsRepository {
    /** Returns a list of Anime Shows */
    suspend fun getAnimeShows(
        page: Int = AppConstants.initialPage,
        limit: Int = AppConstants.defaultLimit
    ): NetworkResult<AnimeListResponse>
}

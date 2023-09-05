package com.murerwa.animeapp.features.shows.domain.repositories

import com.murerwa.animeapp.core.network.DataSourceState
import com.murerwa.animeapp.core.utils.AppConstants
import com.murerwa.animeapp.features.shows.domain.entities.Show

/** An interface for anime operations */
interface AnimeShowsRepository {
    /**
     * A method to get a list of Anime shows.
     *
     * @return [List<Show>]
     */
    suspend fun getAnimeShows(
        page: Int = AppConstants.initialPage,
        limit: Int = AppConstants.defaultLimit
    ): DataSourceState<List<Show>>

    suspend fun refreshShows(
        page: Int = AppConstants.initialPage,
        limit: Int = AppConstants.defaultLimit
    ): DataSourceState<List<Show>>
}

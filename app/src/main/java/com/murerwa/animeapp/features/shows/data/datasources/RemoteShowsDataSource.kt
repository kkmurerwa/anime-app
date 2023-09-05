package com.murerwa.animeapp.features.shows.data.datasources

import com.murerwa.animeapp.core.network.BaseRepository
import com.murerwa.animeapp.core.network.DataSourceState
import com.murerwa.animeapp.features.shows.data.api.AnimeShowsApiClient
import com.murerwa.animeapp.features.shows.data.models.AnimeListResponse
import javax.inject.Inject

/**
 * An interface that fetches shows from the internet.
 *
 * It has two methods; a
 */
interface RemoteShowsDataSource {
    /**
     * This method fetches shows from the internet.
     *
     * @return [NetworkResult<AnimeListResponse>]
     */
    suspend fun fetchShows(): DataSourceState<AnimeListResponse>
}

class RemoteShowsDataSourceImpl @Inject constructor(
    private val apiClient: AnimeShowsApiClient
) : RemoteShowsDataSource, BaseRepository() {
    override suspend fun fetchShows(): DataSourceState<AnimeListResponse> {
        return safeApiCall { apiClient.getShows() }
    }
}
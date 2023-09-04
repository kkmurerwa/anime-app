package com.murerwa.animeapp.features.shows.data.repositories

import com.murerwa.animeapp.core.network.BaseRepository
import com.murerwa.animeapp.core.network.NetworkResult
import com.murerwa.animeapp.features.shows.data.api.AnimeShowsApiClient
import com.murerwa.animeapp.features.shows.domain.models.AnimeListResponse
import com.murerwa.animeapp.features.shows.domain.repositories.AnimeShowsRepository
import javax.inject.Inject

class AnimeShowsRepositoryImpl @Inject constructor(
    private val apiClient: AnimeShowsApiClient
): AnimeShowsRepository, BaseRepository() {
    override suspend fun getAnimeShows(page: Int, limit: Int): NetworkResult<AnimeListResponse> {
        return safeApiCall { apiClient.getShows() }
    }
}

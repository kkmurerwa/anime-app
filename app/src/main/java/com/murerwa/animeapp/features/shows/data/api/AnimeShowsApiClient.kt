package com.murerwa.animeapp.features.shows.data.api

import com.murerwa.animeapp.core.utils.AppConstants
import com.murerwa.animeapp.features.shows.domain.models.AnimeListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeShowsApiClient {
    @GET("anime")
    suspend fun getShows(
        @Query("page") page: Int = AppConstants.initialPage,
        @Query("limit") limit: Int = AppConstants.defaultLimit
    ): AnimeListResponse
}
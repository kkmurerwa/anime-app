package com.murerwa.animeapp.features.search.data.repositories

import com.murerwa.animeapp.core.network.BaseRepository
import com.murerwa.animeapp.core.network.DataSourceState
import com.murerwa.animeapp.features.search.data.api.ImageSearchApiClient
import com.murerwa.animeapp.features.search.domain.models.ImageSearchResponse
import com.murerwa.animeapp.features.search.domain.repositories.ImageSearchRepository
import okhttp3.MultipartBody
import javax.inject.Inject

/** Implementation of the [ImageSearchRepository] class */
class ImageSearchRepositoryImpl @Inject constructor(
    private val apiClient: ImageSearchApiClient
): ImageSearchRepository, BaseRepository() {
    override suspend fun searchImage(image: MultipartBody.Part): DataSourceState<ImageSearchResponse> {
        return safeApiCall { apiClient.searchImage(image) }
    }
}

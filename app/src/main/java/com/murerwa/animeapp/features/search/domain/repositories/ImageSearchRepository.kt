package com.murerwa.animeapp.features.search.domain.repositories

import com.murerwa.animeapp.core.network.DataSourceState
import com.murerwa.animeapp.features.search.domain.models.ImageSearchResponse
import okhttp3.MultipartBody

interface ImageSearchRepository {
    suspend fun searchImage(image: MultipartBody.Part): DataSourceState<ImageSearchResponse>
}
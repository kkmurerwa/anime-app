package com.murerwa.animeapp.features.search.data.api

import com.murerwa.animeapp.features.search.domain.models.ImageSearchResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Part

interface ImageSearchApiClient {
    @GET("search")
    suspend fun searchImage(
        @Part image: MultipartBody.Part
    ): ImageSearchResponse
}
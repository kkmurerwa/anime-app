package com.murerwa.animeapp.features.search.data.api

import com.murerwa.animeapp.features.search.domain.models.ImageSearchResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageSearchApiClient {
    @Multipart
    @POST("search")
    suspend fun searchImage(
        @Part image: MultipartBody.Part
    ): ImageSearchResponse
}
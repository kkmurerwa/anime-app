package com.murerwa.animeapp.features.shows.domain.models

import com.google.gson.annotations.SerializedName

data class JpgImages(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("large_image_url")
    val largeImageUrl: String,
    @SerializedName("small_image_url")
    val smallImageUrl: String
)
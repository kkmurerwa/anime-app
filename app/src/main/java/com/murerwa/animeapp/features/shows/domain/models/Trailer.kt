package com.murerwa.animeapp.features.shows.domain.models

import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("embed_url")
    val embedUrl: String,
    val images: Thumbnails,
    val url: String,
    @SerializedName("youtube_id")
    val youtubeId: String
)
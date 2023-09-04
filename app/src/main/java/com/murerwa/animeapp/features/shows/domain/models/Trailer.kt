package com.murerwa.animeapp.features.shows.domain.models

data class Trailer(
    val embed_url: String,
    val images: Thumbnails,
    val url: String,
    val youtube_id: String
)
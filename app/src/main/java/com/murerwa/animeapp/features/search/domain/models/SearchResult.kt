package com.murerwa.animeapp.features.search.domain.models

data class SearchResult(
    val anilist: Int,
    val episode: Int,
    val filename: String,
    val from: Double,
    val image: String,
    val similarity: Double,
    val to: Double,
    val video: String
)
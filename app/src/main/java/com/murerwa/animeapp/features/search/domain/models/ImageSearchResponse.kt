package com.murerwa.animeapp.features.search.domain.models

data class ImageSearchResponse(
    val error: String,
    val frameCount: Int,
    val result: List<SearchResult>
)
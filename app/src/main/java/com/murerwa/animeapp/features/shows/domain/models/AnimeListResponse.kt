package com.murerwa.animeapp.features.shows.domain.models

data class AnimeListResponse(
    val data: List<Data>,
    val pagination: Pagination
)
package com.murerwa.animeapp.features.shows.domain.models

import com.google.gson.annotations.SerializedName

data class AnimeListResponse(
    @SerializedName("data")
    val shows: List<Show>,
    val pagination: Pagination
)
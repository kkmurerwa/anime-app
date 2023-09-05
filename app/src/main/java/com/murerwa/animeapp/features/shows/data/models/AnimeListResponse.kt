package com.murerwa.animeapp.features.shows.data.models

import com.google.gson.annotations.SerializedName

data class AnimeListResponse(
    @SerializedName("data")
    val shows: List<ShowModel>,
    val pagination: Pagination
)
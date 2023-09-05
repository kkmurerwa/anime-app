package com.murerwa.animeapp.features.shows.data.models

import com.google.gson.annotations.SerializedName

data class Items(
    val count: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val total: Int
)
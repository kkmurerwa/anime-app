package com.murerwa.animeapp.features.shows.data.models

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("has_next_page")
    val hasNextPage: Boolean,
    val items: Items,
    @SerializedName("last_visible_page")
    val lastVisiblePage: Int
)
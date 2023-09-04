package com.murerwa.animeapp.features.shows.domain.models

import com.google.gson.annotations.SerializedName

data class Show(
    val airing: Boolean,
    val approved: Boolean,
    val background: String,
    val favorites: Int,
    @SerializedName("mal_id")
    val id: Int,
    val images: Images,
    val members: Int,
    val popularity: Int,
    val rank: Int,
    val rating: String,
    val score: Double,
    @SerializedName("scored_by")
    val scoredBy: Int,
    val season: String,
    val source: String,
    val status: String,
    val synopsis: String,
    val title: String,
    val trailer: Trailer,
    val type: String,
    val url: String,
    val year: Int
)
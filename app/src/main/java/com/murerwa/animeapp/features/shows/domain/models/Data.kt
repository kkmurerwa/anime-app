package com.murerwa.animeapp.features.shows.domain.models

data class Data(
    val airing: Boolean,
    val approved: Boolean,
    val background: String,
    val favorites: Int,
    val images: Images,
    val mal_id: Int,
    val members: Int,
    val popularity: Int,
    val rank: Int,
    val rating: String,
    val score: Double,
    val scored_by: Int,
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
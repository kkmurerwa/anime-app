package com.murerwa.animeapp.features.shows.data.models

import com.google.gson.annotations.SerializedName
import com.murerwa.animeapp.features.shows.domain.entities.Show

data class ShowModel(
    @SerializedName("mal_id")
    val id: Int,
    val airing: Boolean,
    val background: String,
    val favorites: Int,
    val images: Images,
    val rank: Int,
    val rating: String,
    val score: Double,
    @SerializedName("scored_by")
    val scoredBy: Int?,
    val season: String,
    val source: String,
    val status: String,
    val synopsis: String,
    val title: String,
    val trailer: Trailer,
    val type: String,
    val url: String,
    val year: Int
) {
    fun toShow() = Show(
        id = this.id,
        image = this.images.jpg.largeImageUrl,
        rank = this.rank,
        rating = this.rating,
        score = this.score,
        title = this.title,
        url = this.url,
        year = this.year
    )
}
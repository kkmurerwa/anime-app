package com.murerwa.animeapp.features.shows.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Thumbnails(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("large_image_url")
    val largeImageUrl: String,
    @SerializedName("maximum_image_url")
    val maximumImageUrl: String,
    @SerializedName("medium_image_url")
    val mediumImageUrl: String,
    @SerializedName("small_image_url")
    val smallImageUrl: String
)
package com.murerwa.animeapp.features.shows.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("shows")
data class Show(
    @PrimaryKey
    val id: Int,
    val image: String,
    val rank: Int,
    val rating: String,
    val score: Double,
    val title: String,
    val url: String?,
    val year: Int?
): Parcelable
package com.murerwa.animeapp.features.shows.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.murerwa.animeapp.features.shows.domain.entities.Show

@Dao
interface ShowsDao {
    @Query("SELECT * FROM shows")
    fun getAllShows(): List<Show>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShows(shows: List<Show>)

    @Delete
    fun delete(show: Show)
}
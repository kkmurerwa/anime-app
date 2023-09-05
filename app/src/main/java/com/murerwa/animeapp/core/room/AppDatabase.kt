package com.murerwa.animeapp.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.murerwa.animeapp.features.shows.data.daos.ShowsDao
import com.murerwa.animeapp.features.shows.domain.entities.Show

@Database(entities = [Show::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun showsDao(): ShowsDao
}

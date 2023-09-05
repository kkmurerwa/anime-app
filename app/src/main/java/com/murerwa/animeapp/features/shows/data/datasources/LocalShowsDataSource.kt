package com.murerwa.animeapp.features.shows.data.datasources

import com.murerwa.animeapp.features.shows.data.daos.ShowsDao
import com.murerwa.animeapp.features.shows.domain.entities.Show
import javax.inject.Inject

/**
 * An interface that interacts with the local SQLite DB.
 *
 * It has two methods, [saveShowsToDb] and [getCachedShows]
 *
 * The [saveShowsToDb] stores data fetched from the internet to the db.
 * The [getCachedShows] method that retrieves cached shows from the db.
 */
interface LocalShowsDataSource {
    /**
     * A method to fetch cached shows.
     *
     * @return [List<Show>]
     */
    suspend fun getCachedShows(): List<Show>

    /**
     * A method to store shows to the db.
     *
     * @param [List<Show>]
     */
    suspend fun saveShowsToDb(shows: List<Show>)
}

/**
 * The implementation of the [LocalShowsDataSource] interface.
 */
class LocalShowsDataSourceImpl @Inject constructor(
    private val dao: ShowsDao
) : LocalShowsDataSource {
    override suspend fun getCachedShows(): List<Show> {
        return dao.getAllShows()
    }

    override suspend fun saveShowsToDb(shows: List<Show>) {
        dao.insertShows(shows)
    }
}

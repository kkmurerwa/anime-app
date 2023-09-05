package com.murerwa.animeapp.features.shows.data.repositories

import com.murerwa.animeapp.core.network.BaseRepository
import com.murerwa.animeapp.core.network.DataSourceState
import com.murerwa.animeapp.features.shows.data.datasources.LocalShowsDataSource
import com.murerwa.animeapp.features.shows.data.datasources.RemoteShowsDataSource
import com.murerwa.animeapp.features.shows.domain.entities.Show
import com.murerwa.animeapp.features.shows.domain.repositories.AnimeShowsRepository
import timber.log.Timber
import javax.inject.Inject

class AnimeShowsRepositoryImpl @Inject constructor(
    private val localShowsDataSource: LocalShowsDataSource,
    private val remoteShowsDataSource: RemoteShowsDataSource
): AnimeShowsRepository, BaseRepository() {
    override suspend fun getAnimeShows(page: Int, limit: Int): DataSourceState<List<Show>> {
        try {
            val cachedShows = localShowsDataSource.getCachedShows()

            if (cachedShows.isEmpty()) {
                return getShowsFromNetwork()
            }

            Timber.d("AnimeShowsRepositoryImpl: Shows in DB")

            return DataSourceState.Success(cachedShows)
        } catch (e: Exception) {
            return DataSourceState.Failure(
                isNetworkError = false,
                errorCode = null,
                errorBody = "A database error occurred: $e",
            )
        }
    }

    override suspend fun refreshShows(page: Int, limit: Int): DataSourceState<List<Show>> {
        return getShowsFromNetwork()
    }

    private suspend fun getShowsFromNetwork(): DataSourceState<List<Show>> {
        Timber.d("AnimeShowsRepositoryImpl: Shows fetched from web")
        return when(val networkShows = remoteShowsDataSource.fetchShows()) {
            is DataSourceState.Success -> {
                val shows = networkShows.value.shows.map { showModel ->
                    showModel.toShow()
                }

                localShowsDataSource.saveShowsToDb(shows)

                DataSourceState.Success(shows)
            }
            is DataSourceState.Failure -> {
                DataSourceState.Failure(
                    isNetworkError = false,
                    errorCode = null,
                    errorBody = "Network call failed"
                )
            }
        }
    }
}

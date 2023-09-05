package com.murerwa.animeapp.core.network

/**
 * An interface to enable conversion between remote data source states and local data source states.
 */

sealed class DataSourceState<out T> {
    data class Success<out T>(val value: T): DataSourceState<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: String?
    ): DataSourceState<Nothing>()
}

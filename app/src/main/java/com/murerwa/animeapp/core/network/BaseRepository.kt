package com.murerwa.animeapp.core.network

import com.murerwa.rickandmortytesting.core.utils.readError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber

/**
 * The [BaseRepository] class helps wrap around API requests to provide safe and crash-free
 * API calls.
 */
open class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): DataSourceState<T> {
        return withContext(Dispatchers.IO) {
            try {
                DataSourceState.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                Timber.d(throwable.toString())
                when (throwable) {
                    is HttpException -> {
                        DataSourceState.Failure(
                            false,
                            throwable.code(),
                            throwable.response()?.errorBody().readError()
                        )
                    }
                    else -> {
                        DataSourceState.Failure(true, null, null)
                    }
                }
            }
        }
    }
}
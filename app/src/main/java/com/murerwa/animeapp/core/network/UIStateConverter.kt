package com.murerwa.animeapp.core.network

import android.app.Application
import com.murerwa.rickandmortytesting.core.utils.readError
import timber.log.Timber

fun <T> convertToUIState(
    response: NetworkResult<T>,
    app: Application,
    errorMessage: String = "",
    onSuccess: (T) -> Unit = { }
): UIState<T> {

    val error = errorMessage.ifEmpty {
        "We encountered an unexpected error."
    }

    return when (response) {
        is NetworkResult.Success -> {
            onSuccess.invoke(response.value)

            UIState.Success(response.value)
        }
        is NetworkResult.Failure -> {
            if (response.isNetworkError) {
                UIState.Error("We encountered a network error.")
            } else {
                if (response.errorBody != null) {
                    val sentError = response.errorBody.readError()

                    Timber.d("Response is Not Null: $sentError")

                    Timber.d("Sent Error: $sentError")

                    if (sentError.isNullOrEmpty()) {
                        UIState.Error(error)
                    } else {
                        UIState.Error(sentError)
                    }

                } else {
                    Timber.d("Response is Null")
                    UIState.Error(error)
                }
            }
        }
    }
}
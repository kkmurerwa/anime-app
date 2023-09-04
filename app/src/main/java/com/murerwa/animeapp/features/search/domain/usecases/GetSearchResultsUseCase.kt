package com.murerwa.animeapp.features.search.domain.usecases

import com.murerwa.animeapp.core.network.NetworkResult
import com.murerwa.animeapp.core.network.UIState
import com.murerwa.animeapp.features.search.domain.models.SearchResult
import com.murerwa.animeapp.features.search.domain.repositories.ImageSearchRepository
import com.murerwa.rickandmortytesting.core.utils.readError
import okhttp3.MultipartBody
import javax.inject.Inject

/** Abstraction for easier testing and clean architecture */
interface GetSearchResultsUseCase {
    suspend fun execute(image: MultipartBody.Part): UIState<List<SearchResult>>
}

/** Implementation of the abstract class [GetSearchResultsUseCase] */
class GetSearchResultsUseCaseImpl @Inject constructor(
    private val imageSearchRepository: ImageSearchRepository
): GetSearchResultsUseCase {
    override suspend fun execute(image: MultipartBody.Part): UIState<List<SearchResult>> {
        return when (val searchResults = imageSearchRepository.searchImage(image)) {
            is NetworkResult.Success -> UIState.Success(searchResults.value.result)
            is NetworkResult.Failure -> UIState.Error(
                errorMessage = searchResults.errorBody.readError(),
                isNetworkError = searchResults.isNetworkError
            )
        }
    }
}

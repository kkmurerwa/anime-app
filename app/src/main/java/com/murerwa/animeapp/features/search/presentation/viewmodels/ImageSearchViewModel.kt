package com.murerwa.animeapp.features.search.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murerwa.animeapp.core.network.UIState
import com.murerwa.animeapp.features.search.domain.models.SearchResult
import com.murerwa.animeapp.features.search.domain.usecases.GetSearchResultsUseCase
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

class ImageSearchViewModel @Inject constructor(
    private val useCase: GetSearchResultsUseCase
): ViewModel() {
    val searchResults = MutableLiveData<UIState<List<SearchResult>>>(UIState.Loading)

    fun searchWithImage(image: MultipartBody.Part) = viewModelScope.launch {
        searchResults.value = useCase.execute(image)
    }
}
package com.murerwa.animeapp.features.shows.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murerwa.animeapp.core.network.UIState
import com.murerwa.animeapp.features.shows.domain.entities.Show
import com.murerwa.animeapp.features.shows.domain.usecases.GetAnimeShowsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnimeShowsViewModel @Inject constructor(
    private val getAnimeShowsUseCase: GetAnimeShowsUseCase
): ViewModel() {
    val shows = MutableLiveData<UIState<List<Show>>>(UIState.Loading)

    fun getShows(refresh: Boolean) = viewModelScope.launch {
        shows.value = getAnimeShowsUseCase.execute(refresh)
    }
}
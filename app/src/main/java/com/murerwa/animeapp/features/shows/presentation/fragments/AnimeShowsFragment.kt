package com.murerwa.animeapp.features.shows.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.murerwa.animeapp.R
import com.murerwa.animeapp.core.network.UIState
import com.murerwa.animeapp.databinding.FragmentAnimeShowsBinding
import com.murerwa.animeapp.features.shows.domain.models.Show
import com.murerwa.animeapp.features.shows.presentation.adapters.AnimeShowsAdapter
import com.murerwa.animeapp.features.shows.presentation.viewmodels.AnimeShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AnimeShowsFragment: Fragment(R.layout.fragment_anime_shows) {
    @Inject
    lateinit var viewModel: AnimeShowsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchShows()
    }

    private fun fetchShows() {
        viewModel.getShows()

        viewModel.shows.observe(viewLifecycleOwner) { uistate ->
            when (uistate) {
                is UIState.Loading -> {}
                is UIState.Success ->{
                    val shows = uistate.value

                    bindViews(shows)
                }
                is UIState.Error -> {}
            }
        }
    }

    private fun bindViews(shows: List<Show>) {
        var binding = FragmentAnimeShowsBinding.bind(requireView())

        val adapter = AnimeShowsAdapter(shows)
        binding.recyclerViewShows.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
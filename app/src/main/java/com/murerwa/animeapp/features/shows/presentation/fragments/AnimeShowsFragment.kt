package com.murerwa.animeapp.features.shows.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.murerwa.animeapp.R
import com.murerwa.animeapp.core.network.UIState
import com.murerwa.animeapp.databinding.FragmentAnimeShowsBinding
import com.murerwa.animeapp.features.shows.domain.entities.Show
import com.murerwa.animeapp.features.shows.presentation.adapters.AnimeShowsAdapter
import com.murerwa.animeapp.features.shows.presentation.viewmodels.AnimeShowsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class AnimeShowsFragment: Fragment(R.layout.fragment_anime_shows) {
    @Inject
    lateinit var viewModel: AnimeShowsViewModel

    private var _binding: FragmentAnimeShowsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAnimeShowsBinding.bind(requireView())


        binding.swipeRefreshLayoutShows.setOnRefreshListener {
            fetchShows(refresh = true)
        }

        fetchShows(false)
    }

    private fun fetchShows(refresh: Boolean = false) {
        viewModel.getShows(refresh)

        viewModel.shows.observe(viewLifecycleOwner) { uistate ->
            when (uistate) {
                is UIState.Loading -> {
                    if (!refresh) {
                        binding.progressBarShows.isVisible = true
                    }
                }
                is UIState.Success ->{
                    val shows = uistate.value

                    Timber.d("Shows: $shows")

                    bindRecyclerView(shows)

                    binding.swipeRefreshLayoutShows.isRefreshing = false
                    binding.progressBarShows.isVisible = false
                }
                is UIState.Error -> {
                    binding.swipeRefreshLayoutShows.isRefreshing = false
                    binding.progressBarShows.isVisible = false
                    Timber.d("Shows: Error occurred - ${uistate.errorMessage}")
                }
            }
        }
    }

    private fun bindRecyclerView(shows: List<Show>) {
        val adapter = AnimeShowsAdapter(shows)
        binding.recyclerViewShows.adapter = adapter
    }

    override fun onDestroy() {
        _binding = null

        super.onDestroy()
    }
}
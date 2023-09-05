package com.murerwa.animeapp.features.shows.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.murerwa.animeapp.R
import com.murerwa.animeapp.databinding.FragmentAnimeShowsBinding
import com.murerwa.animeapp.features.shows.presentation.adapters.AnimeShowsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeShowsFragment: Fragment(R.layout.fragment_anime_shows) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var binding = FragmentAnimeShowsBinding.bind(view)

        bindViews(binding)
    }

    private fun bindViews(binding: FragmentAnimeShowsBinding) {
        val adapter = AnimeShowsAdapter(
            emptyList()
        )
        binding.recyclerViewShows.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
package com.murerwa.animeapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.murerwa.animeapp.core.network.UIState
import com.murerwa.animeapp.features.shows.domain.usecases.GetAnimeShowsUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var getAnimeShowsUseCase: GetAnimeShowsUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            val shows = getAnimeShowsUseCase.execute()
            when (shows) {
                is UIState.Success -> {
                    Timber.d("Shows: ${shows.value}")
                }
                else -> {}
            }

        }
    }
}
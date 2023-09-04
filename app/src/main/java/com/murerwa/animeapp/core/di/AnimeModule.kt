package com.murerwa.animeapp.core.di

import com.murerwa.animeapp.core.network.Urls
import com.murerwa.animeapp.features.shows.data.api.AnimeShowsApiClient
import com.murerwa.animeapp.features.shows.data.repositories.AnimeShowsRepositoryImpl
import com.murerwa.animeapp.features.shows.domain.repositories.AnimeShowsRepository
import com.murerwa.animeapp.features.shows.domain.usecases.GetAnimeShowsUseCase
import com.murerwa.animeapp.features.shows.domain.usecases.GetAnimeShowsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val client = OkHttpClient()

@Module
@InstallIn(ActivityComponent::class)
class AnimeModule {
    /** Retrofit */
    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Urls.BASE_URL_SHOWS)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /** API Clients */
    @Provides
    fun providesAnimeShowsApiClient(retrofit: Retrofit): AnimeShowsApiClient =
        retrofit.create(AnimeShowsApiClient::class.java)

    /** Repositories */
    @Provides
    fun providesAnimeShowsRepository(
        apiClient: AnimeShowsApiClient
    ): AnimeShowsRepository = AnimeShowsRepositoryImpl(apiClient)

    /** Use Cases */
    @Provides
    fun providesGetAnimeShowsUseCase(
        animeShowsRepository: AnimeShowsRepository
    ): GetAnimeShowsUseCase = GetAnimeShowsUseCaseImpl(animeShowsRepository)
}
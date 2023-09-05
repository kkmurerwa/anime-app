package com.murerwa.animeapp.core.di

import com.murerwa.animeapp.core.network.Urls
import com.murerwa.animeapp.features.search.data.api.ImageSearchApiClient
import com.murerwa.animeapp.features.search.data.repositories.ImageSearchRepositoryImpl
import com.murerwa.animeapp.features.search.domain.repositories.ImageSearchRepository
import com.murerwa.animeapp.features.search.domain.usecases.GetSearchResultsUseCase
import com.murerwa.animeapp.features.search.domain.usecases.GetSearchResultsUseCaseImpl
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
import javax.inject.Qualifier

val client = OkHttpClient()

@Module
@InstallIn(ActivityComponent::class)
class AnimeModule {
    /** Retrofit */
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RetrofitShows

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RetrofitSearch

    @RetrofitShows
    @Provides
    fun providesRetrofitShows(): Retrofit = Retrofit.Builder()
        .baseUrl(Urls.BASE_URL_SHOWS)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @RetrofitSearch
    @Provides
    fun providesRetrofitSearch(): Retrofit = Retrofit.Builder()
        .baseUrl(Urls.BASE_URL_SEARCH)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /** API Clients */
    @Provides
    fun providesAnimeShowsApiClient(
        @RetrofitShows retrofit: Retrofit
    ): AnimeShowsApiClient = retrofit.create(AnimeShowsApiClient::class.java)

    @Provides
    fun providesImageSearchApiClient(
        @RetrofitSearch retrofit: Retrofit
    ): ImageSearchApiClient =
        retrofit.create(ImageSearchApiClient::class.java)

    /** Repositories */
    @Provides
    fun providesAnimeShowsRepository(
        apiClient: AnimeShowsApiClient
    ): AnimeShowsRepository = AnimeShowsRepositoryImpl(apiClient)

    @Provides
    fun providesImageSearchRepository(
        apiClient: ImageSearchApiClient
    ): ImageSearchRepository = ImageSearchRepositoryImpl(apiClient)

    /** Use Cases */
    @Provides
    fun providesGetAnimeShowsUseCase(
        animeShowsRepository: AnimeShowsRepository
    ): GetAnimeShowsUseCase = GetAnimeShowsUseCaseImpl(animeShowsRepository)

    @Provides
    fun providesGetSearchResultsUseCase(
        imageSearchRepository: ImageSearchRepository
    ): GetSearchResultsUseCase = GetSearchResultsUseCaseImpl(imageSearchRepository)
}
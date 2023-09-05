package com.murerwa.animeapp.core.di

import android.content.Context
import androidx.room.Room
import com.murerwa.animeapp.core.network.Urls
import com.murerwa.animeapp.core.room.AppDatabase
import com.murerwa.animeapp.features.search.data.api.ImageSearchApiClient
import com.murerwa.animeapp.features.search.data.repositories.ImageSearchRepositoryImpl
import com.murerwa.animeapp.features.search.domain.repositories.ImageSearchRepository
import com.murerwa.animeapp.features.search.domain.usecases.GetSearchResultsUseCase
import com.murerwa.animeapp.features.search.domain.usecases.GetSearchResultsUseCaseImpl
import com.murerwa.animeapp.features.shows.data.api.AnimeShowsApiClient
import com.murerwa.animeapp.features.shows.data.daos.ShowsDao
import com.murerwa.animeapp.features.shows.data.datasources.LocalShowsDataSource
import com.murerwa.animeapp.features.shows.data.datasources.LocalShowsDataSourceImpl
import com.murerwa.animeapp.features.shows.data.datasources.RemoteShowsDataSource
import com.murerwa.animeapp.features.shows.data.datasources.RemoteShowsDataSourceImpl
import com.murerwa.animeapp.features.shows.data.repositories.AnimeShowsRepositoryImpl
import com.murerwa.animeapp.features.shows.domain.repositories.AnimeShowsRepository
import com.murerwa.animeapp.features.shows.domain.usecases.GetAnimeShowsUseCase
import com.murerwa.animeapp.features.shows.domain.usecases.GetAnimeShowsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

val client = OkHttpClient()

@Module
@InstallIn(SingletonComponent::class)
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

    /** Room */
    @Provides
    fun providesShowsDao(
        appDatabase: AppDatabase
    ): ShowsDao = appDatabase.showsDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "anime-database"
        ).allowMainThreadQueries()
            .build()
    }

    /** Data sources */
    @Provides
    fun providesRemoteShowsDataSource(
        apiClient: AnimeShowsApiClient
    ): RemoteShowsDataSource = RemoteShowsDataSourceImpl(apiClient)

    @Provides
    fun providesLocalShowsDataSource(
        showsDao: ShowsDao
    ): LocalShowsDataSource = LocalShowsDataSourceImpl(showsDao)

    /** Repositories */
    @Provides
    fun providesAnimeShowsRepository(
        localShowsDataSource: LocalShowsDataSource,
        remoteShowsDataSource: RemoteShowsDataSource
    ): AnimeShowsRepository = AnimeShowsRepositoryImpl(
        localShowsDataSource = localShowsDataSource,
        remoteShowsDataSource = remoteShowsDataSource
    )

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
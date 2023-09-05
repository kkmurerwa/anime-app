package com.murerwa.animeapp.features.shows.fixtures

import com.murerwa.animeapp.core.network.DataSourceState
import com.murerwa.animeapp.core.network.UIState
import com.murerwa.animeapp.features.shows.data.models.Images
import com.murerwa.animeapp.features.shows.data.models.Items
import com.murerwa.animeapp.features.shows.data.models.JpgImages
import com.murerwa.animeapp.features.shows.data.models.Pagination
import com.murerwa.animeapp.features.shows.data.models.ShowModel
import com.murerwa.animeapp.features.shows.data.models.Thumbnails
import com.murerwa.animeapp.features.shows.data.models.Trailer

val tPagination = Pagination(
    currentPage = 1,
    hasNextPage = true,
    items = Items(
        count = 1,
        perPage = 2,
        total = 2
    ),
    lastVisiblePage = 1
)

val tShowModel = ShowModel(
    airing = false,
    background = "",
    favorites = 1,
    id = 1,
    rank = 1,
    rating = "1",
    score = Double.MIN_VALUE,
    scoredBy = 1,
    season = "1",
    source = "",
    status = "",
    synopsis = "",
    title = "",
    trailer = Trailer(
        embedUrl = "",
        url = "",
        youtubeId = "",
        images = Thumbnails(
            imageUrl = "",
            largeImageUrl = "",
            maximumImageUrl = "",
            mediumImageUrl = "",
            smallImageUrl = ""
        )
    ),
    type = "",
    url = "",
    images = Images(
        jpg = JpgImages(
            imageUrl = "",
            largeImageUrl = "",
            smallImageUrl = ""
        )
    ),
    year = 2023
)

val tExpectedNetworkResultFailure = DataSourceState.Failure(
    isNetworkError = false,
    errorCode = 400,
    errorBody = null
)

val tExpectedUIStateError = UIState.Error(
    errorMessage = "Test error message"
)
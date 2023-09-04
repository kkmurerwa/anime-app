package com.murerwa.animeapp.features.shows.fixtures

import com.murerwa.animeapp.core.network.NetworkResult
import com.murerwa.animeapp.core.network.UIState
import com.murerwa.animeapp.features.shows.domain.models.Images
import com.murerwa.animeapp.features.shows.domain.models.Items
import com.murerwa.animeapp.features.shows.domain.models.JpgImages
import com.murerwa.animeapp.features.shows.domain.models.Pagination
import com.murerwa.animeapp.features.shows.domain.models.Show
import com.murerwa.animeapp.features.shows.domain.models.Thumbnails
import com.murerwa.animeapp.features.shows.domain.models.Trailer

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

val tShow = Show(
    airing = false,
    approved = true,
    background = "",
    favorites = 1,
    id = 1,
    images = Images(
        jpg = JpgImages(
            imageUrl = "",
            largeImageUrl = "",
            smallImageUrl = ""
        )
    ),
    members = 1,
    popularity = 1,
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
        images = Thumbnails(
            imageUrl = "",
            largeImageUrl = "",
            maximumImageUrl = "",
            mediumImageUrl = "",
            smallImageUrl = "",
        ),
        url = "",
        youtubeId = ""
    ),
    type = "",
    url = "",
    year = 2021
)

val tExpectedNetworkResultFailure = NetworkResult.Failure(
    isNetworkError = false,
    errorCode = 400,
    errorBody = null
)

val tExpectedUIStateError = UIState.Error(
    errorMessage = "Test error message"
)
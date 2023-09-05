package com.murerwa.animeapp.features.shows.fixtures

import com.murerwa.animeapp.core.network.DataSourceState
import com.murerwa.animeapp.core.network.UIState
import com.murerwa.animeapp.features.shows.domain.entities.Show

//val tPagination = Pagination(
//    currentPage = 1,
//    hasNextPage = true,
//    items = Items(
//        count = 1,
//        perPage = 2,
//        total = 2
//    ),
//    lastVisiblePage = 1
//)

val tShow = Show(
    airing = false,
    background = "",
    favorites = 1,
    id = 1,
    image = "",
    rank = 1,
    rating = "1",
    score = Double.MIN_VALUE,
    scoredBy = 1,
    season = "1",
    source = "",
    status = "",
    synopsis = "",
    title = "",
    trailer = "",
    type = "",
    url = "",
    year = 2021
)

val tExpectedNetworkResultFailure = DataSourceState.Failure(
    isNetworkError = false,
    errorCode = 400,
    errorBody = null
)

val tExpectedUIStateError = UIState.Error(
    errorMessage = "Test error message"
)
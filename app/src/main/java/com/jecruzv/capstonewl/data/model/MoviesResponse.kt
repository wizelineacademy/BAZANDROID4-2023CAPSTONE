package com.jecruzv.capstonewl.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesResponse(
    val page: Int?,
    val results: List<Movie>?,
    val total_pages: Int?,
    val total_results: Int?
)

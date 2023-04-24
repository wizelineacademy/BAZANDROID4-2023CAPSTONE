package com.edith.movies.core.data.database.model

import com.edith.movies.core.data.database.model.MovieModel

data class TopRatedMoviesResponse(
    val page: Int,
    val results: List<MovieModel>,
    val total_pages: Int,
    val total_results: Int
)
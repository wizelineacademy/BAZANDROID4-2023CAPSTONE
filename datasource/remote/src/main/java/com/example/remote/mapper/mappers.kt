package com.example.remote.mapper

import com.example.core.model.GenreModel
import com.example.core.model.MovieModel
import com.example.remote.response.BASE_URL_POSTER
import com.example.remote.response.GenresResponse
import com.example.remote.response.LatestResponse
import com.example.remote.response.MovieResponse

fun MovieResponse.transformToDomain(): List<MovieModel> {
    return results.map {
        MovieModel(
            title = it.title,
            imageUrl = BASE_URL_POSTER.plus(it.posterPath),
            description = it.overview,
            genreIds = it.genreIds
        )
    }
}

fun LatestResponse.transformToDomain(): MovieModel {
    return MovieModel(
        title = title.orEmpty(),
        imageUrl = BASE_URL_POSTER.plus(poster_path.orEmpty()),
        description = overview.orEmpty()
    )
}

fun GenresResponse.transformToDomain(): List<GenreModel> {
    return genres?.map {
        GenreModel(
            id = it.id ?: 0,
            name = it.name.orEmpty()
        )
    } ?: emptyList()
}
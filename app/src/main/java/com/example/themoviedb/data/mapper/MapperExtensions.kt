package com.example.themoviedb.data.mapper

import com.example.themoviedb.data.datasource.local.NowPlayingLocal
import com.example.themoviedb.data.datasource.local.TopRatedLocal
import com.example.themoviedb.data.model.BASE_URL_POSTER
import com.example.themoviedb.data.model.LatestResponse
import com.example.themoviedb.data.model.MovieResponse
import com.example.themoviedb.domain.MovieModel

fun List<MovieModel>.toNowPlayingEntities(): List<NowPlayingLocal> {
    return mapIndexed { index, movieModel ->
        NowPlayingLocal(
            id = index,
            title = movieModel.title,
            posterPath = movieModel.imageUrl
        )
    }
}

fun List<MovieModel>.toTopRatedEntities(): List<TopRatedLocal> {
    return mapIndexed { index, movieModel ->
        TopRatedLocal(
            id = index,
            title = movieModel.title,
            posterPath = movieModel.imageUrl
        )
    }
}

fun MovieResponse.transformToDomain(): List<MovieModel> {
    return results.map {
        MovieModel(
            title = it.title,
            imageUrl = BASE_URL_POSTER.plus(it.posterPath)
        )
    }
}

fun LatestResponse.transformToDomain(): MovieModel {
    return MovieModel(
        title = title.orEmpty(),
        imageUrl = BASE_URL_POSTER.plus(poster_path.orEmpty())
    )
}
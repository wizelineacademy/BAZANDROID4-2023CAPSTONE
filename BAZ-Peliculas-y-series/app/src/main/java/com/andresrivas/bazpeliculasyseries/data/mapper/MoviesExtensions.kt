package com.andresrivas.bazpeliculasyseries.data.mapper

import com.andresrivas.bazpeliculasyseries.data.services.moviesapi.response.*
import com.andresrivas.bazpeliculasyseries.domain.model.*

fun MovieResponse.transformToDomain(): MoviesPagesModel {
    return MoviesPagesModel(
        dates?.transformToDomain() ?: DatesResultModel(),
        page ?: 0,
        resultList.map { it?.transformToDomain() ?: MovieModel(id = 0) })
}

fun MovieResult.transformToDomain(): MovieModel {
    return MovieModel(
        0,
        adult ?: false,
        backdropPath.orEmpty(),
        //genreIds ?: arrayListOf(),
        id.orEmpty(),
        originalLanguage.orEmpty(),
        originalTitle.orEmpty(),
        overView.orEmpty(),
        popularity ?: 0.0,
        posterPath.orEmpty(),
        releaseDate.orEmpty(),
        title.orEmpty(),
        video ?: false,
        voteAverage ?: 0.0,
        voteCount ?: 0.0,
    )
}

fun MovieVideoResponse.transformToDomain(): MoviesVideoModel {
    return MoviesVideoModel(
        id ?: 0,
        resultList.map { it?.transformToDomain() ?: MoviesVideoResultModel() })
}

fun MovieVideoResult.transformToDomain(): MoviesVideoResultModel {
    return MoviesVideoResultModel(
        id.orEmpty(),
        key.orEmpty(),
        name.orEmpty(),
        site.orEmpty(),
        size ?: 0,
        type.orEmpty()
    )
}

fun Dates.transformToDomain(): DatesResultModel {
    return DatesResultModel(maximum.orEmpty(), minimum.orEmpty())
}
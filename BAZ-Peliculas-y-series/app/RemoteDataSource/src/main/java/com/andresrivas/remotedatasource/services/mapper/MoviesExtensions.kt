package com.andresrivas.remotedatasource.services.mapper

import com.andresrivas.bazpeliculasyseries.domain.model.DatesResultModel
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesPagesModel
import com.andresrivas.common.model.MoviesVideoModel
import com.andresrivas.common.model.MoviesVideoResultModel
import com.andresrivas.remotedatasource.services.moviesapi.response.MovieResponse

fun MovieResponse.transformToDomain(): MoviesPagesModel {
    return MoviesPagesModel(
        dates?.transformToDomain() ?: DatesResultModel(),
        page ?: 0,
        resultList.map { it?.transformToDomain() ?: MovieModel(id = 0) },
    )
}

fun com.andresrivas.remotedatasource.services.moviesapi.response.MovieResult.transformToDomain(): MovieModel {
    return MovieModel(
        0,
        adult ?: false,
        backdropPath.orEmpty(),
        // genreIds ?: arrayListOf(),
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

fun com.andresrivas.remotedatasource.services.moviesapi.response.MovieVideoResponse.transformToDomain(): MoviesVideoModel {
    return MoviesVideoModel(
        id ?: 0,
        resultList.map { it?.transformToDomain() ?: MoviesVideoResultModel() },
    )
}

fun com.andresrivas.remotedatasource.services.moviesapi.response.MovieVideoResult.transformToDomain(): MoviesVideoResultModel {
    return MoviesVideoResultModel(
        id.orEmpty(),
        key.orEmpty(),
        name.orEmpty(),
        site.orEmpty(),
        size ?: 0,
        type.orEmpty(),
    )
}

fun com.andresrivas.remotedatasource.services.moviesapi.response.Dates.transformToDomain(): DatesResultModel {
    return DatesResultModel(maximum.orEmpty(), minimum.orEmpty())
}

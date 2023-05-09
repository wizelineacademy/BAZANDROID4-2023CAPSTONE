package com.andresrivas.bazpeliculasyseries.data.mapper

import com.andresrivas.bazpeliculasyseries.data.services.moviesapi.response.LatestMoviesResponse
import com.andresrivas.bazpeliculasyseries.domain.model.LatestMoviesModel
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel

fun LatestMoviesResponse.transformToDomain(): LatestMoviesModel {
    return LatestMoviesModel(
        adult ?: false,
        backdropPath.orEmpty(),
        belongsToCollection.orEmpty(),
        budget ?: 0,
        genres?.map { it.transformToDomain() } ?: listOf(),
        homepage.orEmpty(),
        id ?: 0,
        imdbId.orEmpty(),
        originalLanguage.orEmpty(),
        originalTitle.orEmpty(),
        overview.orEmpty(),
        popularity ?: 0,
        posterPath.orEmpty(),
        productionCompanies?.map {
            it?.transformToDomain() ?: LatestMoviesModel.ProductionCompanies()
        } ?: arrayListOf(),
        productionCountries?.map { it.orEmpty() } ?: arrayListOf(),
        releaseDate.orEmpty(),
        revenue ?: 0,
        runtime ?: 0,
        spokenLanguages?.map {
            it?.transformToDomain() ?: LatestMoviesModel.SpokenLanguages()
        } ?: arrayListOf(),
        status.orEmpty(),
        tagline.orEmpty(),
        title.orEmpty(),
        video ?: false,
        voteAverage ?: 0,
        voteCount ?: 0,
    )
}

fun LatestMoviesResponse.Genres.transformToDomain(): LatestMoviesModel.Genres {
    return LatestMoviesModel.Genres(id ?: 0, name.orEmpty())
}

fun LatestMoviesResponse.ProductionCompanies.transformToDomain(): LatestMoviesModel.ProductionCompanies {
    return LatestMoviesModel.ProductionCompanies(
        id ?: 0,
        logoPath.orEmpty(),
        name.orEmpty(),
        originCountry.orEmpty(),
    )
}

fun LatestMoviesResponse.SpokenLanguages.transformToDomain(): LatestMoviesModel.SpokenLanguages {
    return LatestMoviesModel.SpokenLanguages(
        englishName.orEmpty(),
        iso.orEmpty(),
        name.orEmpty(),
    )
}

fun LatestMoviesModel.transformToDetail(): MovieModel {
    return MovieModel(id = id, apiId = id.toString(), overView = overview, title = title)
}

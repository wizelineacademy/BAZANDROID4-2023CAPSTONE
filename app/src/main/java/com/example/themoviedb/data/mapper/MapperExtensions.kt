package com.example.themoviedb.data.mapper

import com.example.themoviedb.data.datasource.local.*
import com.example.themoviedb.data.model.BASE_URL_POSTER
import com.example.themoviedb.data.model.GenresResponse
import com.example.themoviedb.data.model.LatestResponse
import com.example.themoviedb.data.model.MovieResponse
import com.example.themoviedb.domain.GenreModel
import com.example.themoviedb.domain.MovieModel

fun List<MovieModel>.toNowPlayingEntities(): List<NowPlayingLocal> {
    return mapIndexed { index, movieModel ->
        NowPlayingLocal(
            id = index,
            title = movieModel.title,
            posterPath = movieModel.imageUrl,
            description = movieModel.description,
            genreIds = movieModel.genreIds
        )
    }
}

fun List<MovieModel>.toTopRatedEntities(): List<TopRatedLocal> {
    return mapIndexed { index, movieModel ->
        TopRatedLocal(
            id = index,
            title = movieModel.title,
            posterPath = movieModel.imageUrl,
            description = movieModel.description,
            genreIds = movieModel.genreIds
        )
    }
}

fun List<GenreModel>.toGenresEntities(): List<GenreLocal> {
    return map { genreModel ->
        GenreLocal(
            idGenre = genreModel.id,
            name = genreModel.name
        )
    }
}

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

fun List<NowPlayingLocalWithGenres>.toModel(): List<MovieModel> {
    return map {
        MovieModel(
            title = it.nowPlayingLocal.title,
            imageUrl = it.nowPlayingLocal.posterPath,
            description = it.nowPlayingLocal.description,
            genreIds = it.nowPlayingLocal.genreIds,
            genres = it.genres.toModel()
        )
    }
}

@JvmName("toModelGenreLocal")
fun List<GenreLocal>.toModel(): List<GenreModel> {
    return map {
        GenreModel(
            id = it.idGenre,
            name = it.name
        )
    }
}

@JvmName("toModelTopRatedLocal")
fun List<TopRatedLocal>.toModel(): List<MovieModel> {
    return map {
        MovieModel(
            title = it.title,
            imageUrl = it.posterPath,
            description = it.description,
            genreIds = it.genreIds,
        )
    }
}
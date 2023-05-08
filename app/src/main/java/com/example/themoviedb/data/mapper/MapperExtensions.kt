package com.example.themoviedb.data.mapper

import com.example.core.model.GenreModel
import com.example.core.model.MovieModel
import com.example.local.entity.GenreLocal
import com.example.local.entity.NowPlayingLocal
import com.example.local.entity.NowPlayingLocalWithGenres
import com.example.local.entity.TopRatedLocal

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

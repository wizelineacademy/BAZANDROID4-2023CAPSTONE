package com.example.themoviedb.presentation

import com.example.core.model.GenreModel
import com.example.core.model.MovieModel
import com.example.local.entity.GenreLocal
import com.example.local.entity.NowPlayingLocal
import com.example.local.entity.NowPlayingLocalWithGenres

object MockTest {
    val ids = listOf(1, 2)

    val movie = MovieModel(title = "Movie 1")

    val genres = listOf(
        GenreModel(name = "fantasy"),
        GenreModel(name = "adventure")
    )

    val localGenres = listOf(
        GenreLocal(name = "fantasy"),
        GenreLocal(name = "adventure")
    )

    val movies = listOf(
        MovieModel(title = "Movie 1"),
        MovieModel(title = "Movie 2")
    )

    val localMovies = listOf(
        NowPlayingLocalWithGenres(
            NowPlayingLocal(
                id = 1,
                title = "Movie 1",
                description = "",
                posterPath = "",
                genreIds = emptyList()
            ),
            genres = emptyList()
        ),
        NowPlayingLocalWithGenres(
            NowPlayingLocal(
                id = 2,
                title = "Movie 2",
                description = "",
                posterPath = "",
                genreIds = emptyList()
            ),
            genres = emptyList()
        ))
}

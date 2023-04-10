package com.edith.movies.features.movies.domain

import com.edith.movies.core.data.MovieDb
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MoviesDbRepository) {

    suspend operator fun invoke(): List<MovieDb> {
        return repository.getAllMovies()
    }

    /*
    suspend operator fun  invoke():List<MovieDb>{
        return repository.getLastMovies()
    }*/
}
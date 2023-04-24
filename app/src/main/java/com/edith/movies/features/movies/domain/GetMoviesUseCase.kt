package com.edith.movies.features.movies.domain

import com.edith.movies.core.data.database.entity.toDatabase
import com.edith.movies.core.data.database.model.Movie
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MoviesDbRepository) {
    suspend operator fun invoke():List<Movie>{
        val movies = repository.getAllNowPlayingMoviesApi()

       return if (movies.isNotEmpty()){
           repository.clearMovies()
            repository.insertMovies(movies.map { it.toDatabase() })
           movies
        }else{
            repository.getAllNowPlayingMoviesDb()
        }
    }

}
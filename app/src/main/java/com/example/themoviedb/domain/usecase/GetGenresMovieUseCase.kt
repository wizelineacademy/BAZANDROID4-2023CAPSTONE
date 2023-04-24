package com.example.themoviedb.domain.usecase

import com.example.themoviedb.domain.GenreModel
import com.example.themoviedb.domain.MovieRepository
import com.example.themoviedb.util.ResultWrapper
import javax.inject.Inject

class GetGenresMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend fun execute(params: List<Int>): ResultWrapper<List<GenreModel>> {
        return repository.getGenresMovie(params)
    }
}
package com.example.themoviedb.domain

import com.example.core.model.GenreModel
import com.example.core.model.MovieModel
import com.example.tools.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowPlaying(): Flow<ResultWrapper<List<MovieModel>>>
    suspend fun getLatest(): Flow<ResultWrapper<MovieModel>>
    suspend fun getTopRated(): Flow<ResultWrapper<List<MovieModel>>>
    suspend fun getGenres(): Flow<ResultWrapper<List<GenreModel>>>

    suspend fun getGenresMovie(ids: List<Int>): ResultWrapper<List<GenreModel>>
}

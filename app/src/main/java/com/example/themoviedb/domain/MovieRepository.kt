package com.example.themoviedb.domain

import com.example.themoviedb.util.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowPlaying(): Flow<ResultWrapper<List<MovieModel>>>
    suspend fun getLatest()
    suspend fun getTopRated(): ResultWrapper<List<MovieModel>>
}
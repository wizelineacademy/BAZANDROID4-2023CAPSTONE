package com.example.themoviedb.data.datasource.local

import com.example.themoviedb.util.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getNowPlaying(): Flow<List<NowPlayingLocalWithGenres>>
    fun getLatest(): Flow<List<LatestLocal>>
    fun getTopRated(): Flow<List<TopRatedLocal>>

    suspend fun saveNowPlaying(movies: List<NowPlayingLocal>)
    suspend fun saveLatest(movies: List<LatestLocal>)
    suspend fun saveTopRated(movies: List<TopRatedLocal>)
    suspend fun saveGenres(genres: List<GenreLocal>): ResultWrapper<Boolean>

    suspend fun getGenresMovie(ids: List<Int>): ResultWrapper<List<GenreLocal>>
}
package com.example.themoviedb.data.datasource.local

import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getNowPlaying(): Flow<List<NowPlayingLocal>>
    fun getLatest(): Flow<List<LatestLocal>>
    fun getTopRated(): Flow<List<TopRatedLocal>>

    suspend fun saveNowPlaying(movies: List<NowPlayingLocal>)
    suspend fun saveLatest(movies: List<LatestLocal>)
    suspend fun saveTopRated(movies: List<TopRatedLocal>)
}
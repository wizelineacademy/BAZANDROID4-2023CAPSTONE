package com.example.local

import com.example.local.entity.GenreLocal
import com.example.local.entity.LatestLocal
import com.example.local.entity.TopRatedLocal
import com.example.local.entity.NowPlayingLocal
import com.example.local.entity.NowPlayingLocalWithGenres
import com.example.tools.ResultWrapper
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
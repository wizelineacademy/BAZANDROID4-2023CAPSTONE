package com.example.themoviedb.domain

import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getNowPlaying()
    suspend fun getLatest()
    suspend fun getTopRated()

    fun getNowPlayingStream(): Flow<List<MovieModel>>
    fun getLatestStream(): Flow<List<MovieModel>>
    fun getTopRatedStream(): Flow<List<MovieModel>>

    fun callNowPlaying(): Flow<List<MovieModel>>
}
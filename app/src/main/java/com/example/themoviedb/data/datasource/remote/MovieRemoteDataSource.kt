package com.example.themoviedb.data.datasource.remote

import com.example.themoviedb.data.model.MovieResponse
import com.example.themoviedb.util.ResultWrapper

interface MovieRemoteDataSource {
    suspend fun callNowPlayingMovies(): ResultWrapper<MovieResponse>
    suspend fun callLatestMovies(): ResultWrapper<MovieResponse>
    suspend fun callTopRatedMovies(): ResultWrapper<MovieResponse>
}
package com.example.remote

import com.example.core.model.GenreModel
import com.example.core.model.MovieModel
import com.example.tools.ResultWrapper

interface MovieRemoteDataSource {
    suspend fun callNowPlayingMovies(): ResultWrapper<List<MovieModel>>
    suspend fun callLatestMovies(): ResultWrapper<MovieModel>
    suspend fun callTopRatedMovies(): ResultWrapper<List<MovieModel>>
    suspend fun callGenresMovies(): ResultWrapper<List<GenreModel>>
}
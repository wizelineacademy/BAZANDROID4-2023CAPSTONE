package com.example.themoviedb.data.repository

import com.example.themoviedb.data.datasource.local.MovieLocalDataSource
import com.example.themoviedb.data.datasource.local.toModel
import com.example.themoviedb.data.datasource.remote.MovieRemoteDataSource
import com.example.themoviedb.data.mapper.toNowPlayingEntities
import com.example.themoviedb.data.mapper.toTopRatedEntities
import com.example.themoviedb.domain.MovieModel
import com.example.themoviedb.domain.MovieRepository
import com.example.themoviedb.util.ResultWrapper
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
): MovieRepository {
    override suspend fun getNowPlaying(): Flow<ResultWrapper<List<MovieModel>>> {
        return flow {
            val response = remoteDataSource.callNowPlayingMovies()
            if (response is ResultWrapper.Success) {
                localDataSource.saveNowPlaying(response.data.toNowPlayingEntities())
            }
            emit(response)
            localDataSource.getNowPlaying().collect {
                emit(ResultWrapper.Success(it.toModel()))
            }
        }
    }

    override suspend fun getLatest() {

    }

    override suspend fun getTopRated(): ResultWrapper<List<MovieModel>>  {
        val response = remoteDataSource.callTopRatedMovies().apply {
            if (this is ResultWrapper.Success) {
                localDataSource.saveTopRated(data.toTopRatedEntities())
            }
        }
        return response
    }
}
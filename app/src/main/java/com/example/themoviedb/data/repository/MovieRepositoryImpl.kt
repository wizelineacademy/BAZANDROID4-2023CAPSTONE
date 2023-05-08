package com.example.themoviedb.data.repository

import com.example.core.model.GenreModel
import com.example.core.model.MovieModel
import com.example.local.MovieLocalDataSource
import com.example.remote.MovieRemoteDataSource
import com.example.themoviedb.data.mapper.toGenresEntities
import com.example.themoviedb.data.mapper.toModel
import com.example.themoviedb.data.mapper.toNowPlayingEntities
import com.example.themoviedb.data.mapper.toTopRatedEntities
import com.example.themoviedb.domain.MovieRepository
import com.example.tools.ResultWrapper
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {
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

    override suspend fun getLatest(): Flow<ResultWrapper<MovieModel>> {
        return flow {
            emit(remoteDataSource.callLatestMovies())
        }
    }

    override suspend fun getTopRated(): Flow<ResultWrapper<List<MovieModel>>> {
        return flow {
            val response = remoteDataSource.callTopRatedMovies()
            if (response is ResultWrapper.Success) {
                localDataSource.saveTopRated(response.data.toTopRatedEntities())
            }
            emit(response)
            localDataSource.getTopRated().collect {
                emit(ResultWrapper.Success(it.toModel()))
            }
        }
    }

    override suspend fun getGenres(): Flow<ResultWrapper<List<GenreModel>>> {
        return flow {
            val response = remoteDataSource.callGenresMovies()
            if (response is ResultWrapper.Success) {
                localDataSource.saveGenres(response.data.toGenresEntities())
            }
            emit(response)
        }
    }

    override suspend fun getGenresMovie(ids: List<Int>): ResultWrapper<List<GenreModel>> {
        return when (val response = localDataSource.getGenresMovie(ids)) {
            is ResultWrapper.Error -> {
                ResultWrapper.Error(0, response.message)
            }
            is ResultWrapper.Success -> {
                ResultWrapper.Success(response.data.toModel())
            }
        }
    }
}

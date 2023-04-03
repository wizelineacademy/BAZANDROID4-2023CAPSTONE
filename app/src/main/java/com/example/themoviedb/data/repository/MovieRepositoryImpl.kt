package com.example.themoviedb.data.repository

import com.example.themoviedb.data.datasource.local.MovieLocalDataSource
import com.example.themoviedb.data.datasource.local.toModel
import com.example.themoviedb.data.datasource.remote.MovieRemoteDataSource
import com.example.themoviedb.data.model.toLatestEntity
import com.example.themoviedb.data.model.toNowPlayingEntity
import com.example.themoviedb.data.model.toTopRatedEntity
import com.example.themoviedb.domain.MovieModel
import com.example.themoviedb.domain.MovieRepository
import com.example.themoviedb.util.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
): MovieRepository {

    override suspend fun getNowPlaying() {
        withContext(Dispatchers.IO) {
            remoteDataSource.callNowPlayingMovies().let {
                if (it is ResultWrapper.Success) {
                    it.data.results.toNowPlayingEntity().let { movies ->
                        localDataSource.saveNowPlaying(movies)
                    }
                }
            }
        }
    }

    override suspend fun getLatest() {
        withContext(Dispatchers.IO) {
            remoteDataSource.callLatestMovies().let {
                if (it is ResultWrapper.Success) {
                    it.data.results.toLatestEntity().let { movies ->
                        localDataSource.saveLatest(movies)
                    }
                }
            }
        }
    }

    override suspend fun getTopRated() {
        withContext(Dispatchers.IO) {
            remoteDataSource.callTopRatedMovies().let {
                if (it is ResultWrapper.Success) {
                    it.data.results.toTopRatedEntity().let { movies ->
                        localDataSource.saveTopRated(movies)
                    }
                }
            }
        }
    }

    override fun getNowPlayingStream(): Flow<List<MovieModel>> {
        return localDataSource.getNowPlaying().map {
            it.toModel()
        }.flowOn(Dispatchers.IO)
    }

    override fun getLatestStream(): Flow<List<MovieModel>> {
        return localDataSource.getLatest().map {
            it.toModel()
        }.flowOn(Dispatchers.IO)
    }

    override fun getTopRatedStream(): Flow<List<MovieModel>> {
        return localDataSource.getTopRated().map {
                it.toModel()
        }
        .flowOn(Dispatchers.IO)
    }

    override fun callNowPlaying(): Flow<List<MovieModel>> {
        return flow {  }
    }
}
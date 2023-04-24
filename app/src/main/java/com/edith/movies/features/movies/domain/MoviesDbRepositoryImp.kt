package com.edith.movies.features.movies.domain

import com.edith.movies.core.data.database.dao.GenderDao
import com.edith.movies.core.data.database.dao.MoviesDao
import com.edith.movies.core.data.database.entity.MovieEntity
import com.edith.movies.core.data.database.model.*
import com.edith.movies.core.service.api.ApiService
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MoviesDbRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao,
    private val genderDao: GenderDao
) : MoviesDbRepository {


    override suspend fun getAllNowPlayingMoviesApi(): List<Movie> {
        val response = apiService.listNowPlayingMovies("7175cc36abf3c4b497b768214f16ef0b")
        return if (response.isSuccessful){
            response.body()!!.results.map { it.toDomain() }
        }else{
            emptyList()
        }
    }

    override suspend fun getAllNowPlayingMoviesDb(): List<Movie> {
        val response = moviesDao.getAll()
        return response.map { it.toDomain() }
    }

    override suspend fun insertMovies(movies: List<MovieEntity>){
        moviesDao.insertAll(movies)
    }

    override suspend fun clearMovies(){
        moviesDao.deleteAllMovies()
    }

    override suspend fun getlistTopRatedMovies(): List<MovieModel> {
        val response = apiService.listTopRatedMovies("7175cc36abf3c4b497b768214f16ef0b")
        return if (response.isSuccessful){
            response.body()!!.results
        }else{
            emptyList()
        }
    }

    override suspend fun getLastMovie(): LastMoviesResponse? {
        val response = apiService.lastMovie("7175cc36abf3c4b497b768214f16ef0b")
        return if (response.isSuccessful) {
            response.body()
        }else null
    }

    override suspend fun getAllMovies(): List<MovieDb> {
        val response = apiService.listPopularMovies("7175cc36abf3c4b497b768214f16ef0b")
        return if (response.isSuccessful) {
            response.body()!!.results
        } else {
            emptyList()
        }
    }

}
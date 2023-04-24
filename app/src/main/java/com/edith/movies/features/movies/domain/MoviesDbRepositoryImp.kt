package com.edith.movies.features.movies.domain

import com.edith.movies.core.data.database.dao.GenderDao
import com.edith.movies.core.data.database.dao.MoviesDao
import com.edith.movies.core.data.database.model.LastMoviesResponse
import com.edith.movies.core.data.database.model.MovieDb
import com.edith.movies.core.data.database.model.MovieModel
import com.edith.movies.core.service.api.ApiService
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MoviesDbRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val moviesDao: MoviesDao,
    private val genderDao: GenderDao
) : MoviesDbRepository {


//1. Revisar si tenemos informacion en room
 //   2. Si hay informacion utilizar room
 //   3. Si no hay informacion utilizar el servicio y guardar en room las peliculas
    // 4. Mostrar la lista de peliculas

   /* override suspend fun getAllNowPlayingDb(): List<MovieEntity> {
        val moviesDao = moviesDao
        return moviesDao.getAll()
    }*/

    override suspend fun getAllNowPlayingMovies(): List<MovieModel> {
        val response = apiService.listNowPlayingMovies("7175cc36abf3c4b497b768214f16ef0b")
        return if (response.isSuccessful){
            response.body()!!.results
        }else{
            emptyList()
        }
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
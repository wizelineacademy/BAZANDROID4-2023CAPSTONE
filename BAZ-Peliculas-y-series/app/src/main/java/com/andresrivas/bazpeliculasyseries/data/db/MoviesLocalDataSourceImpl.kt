package com.andresrivas.bazpeliculasyseries.data.db

import com.andresrivas.bazpeliculasyseries.data.db.data.base.FavoriteMoviesDb
import com.andresrivas.bazpeliculasyseries.data.db.data.base.MovieDb
import com.andresrivas.bazpeliculasyseries.data.repository.datasource.MoviesLocalDataSource
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import javax.inject.Inject

class MoviesLocalDataSourceImpl @Inject constructor(
    private val moviesDataBase: MovieDb,
    private val favoriteMoviesDb: FavoriteMoviesDb
) : MoviesLocalDataSource {
    override suspend fun saveMoviesPlayingNow(list: List<MovieModel>): ResultAPI<List<MovieModel>> {
        return try {
            moviesDataBase.movieDao().insert(list)
            ResultAPI.OnSuccess(list)
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }

    override suspend fun getMoviesPlayingNow(): ResultAPI<List<MovieModel>> {
        return try {
            ResultAPI.OnSuccess(moviesDataBase.movieDao().getAll())
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }

    override suspend fun saveFavoriteMovies(list: List<MovieModel>): ResultAPI<List<MovieModel>> {
        return try {
            favoriteMoviesDb.favoriteMoviesDao().insert(list)
            ResultAPI.OnSuccess(favoriteMoviesDb.favoriteMoviesDao().getAll())
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }

    override suspend fun saveFavoriteIfNoExistMovies(list: List<MovieModel>): ResultAPI<List<MovieModel>> {
        return try {
            favoriteMoviesDb.favoriteMoviesDao().insertIfNoExist(list)
            ResultAPI.OnSuccess(favoriteMoviesDb.favoriteMoviesDao().getAll())
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }

    override suspend fun removeFavoriteMovies(list: List<MovieModel>): ResultAPI<List<MovieModel>> {
        return try {
            favoriteMoviesDb.favoriteMoviesDao().delete(list)
            ResultAPI.OnSuccess(favoriteMoviesDb.favoriteMoviesDao().getAll())
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }

    override suspend fun getFavoriteMovies(): ResultAPI<List<MovieModel>> {
        return try {
            ResultAPI.OnSuccess(favoriteMoviesDb.favoriteMoviesDao().getAll())
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }

    override suspend fun isFavorite(movie: MovieModel): ResultAPI<Boolean> {
        return try {
            ResultAPI.OnSuccess(favoriteMoviesDb.favoriteMoviesDao().getAll().contains(movie))
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }
}
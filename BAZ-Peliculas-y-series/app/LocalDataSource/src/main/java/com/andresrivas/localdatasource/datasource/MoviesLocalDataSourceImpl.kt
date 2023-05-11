package com.andresrivas.localdatasource.datasource

import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.common.utilities.ResultAPI
import com.andresrivas.localdatasource.db.data.base.FavoriteMoviesDb
import com.andresrivas.localdatasource.db.data.base.NowPlayingMoviesDb
import com.andresrivas.localdatasource.db.data.base.TopRatedMoviesDb
import javax.inject.Inject

class MoviesLocalDataSourceImpl @Inject constructor(
    private val nowPlayingMoviesDataBase: NowPlayingMoviesDb,
    private val topRatedMoviesDataBase: TopRatedMoviesDb,
    private val favoriteMoviesDb: FavoriteMoviesDb,
) : MoviesLocalDataSource {
    override suspend fun saveMoviesPlayingNow(list: List<MovieModel>): ResultAPI<List<MovieModel>> {
        return try {
            nowPlayingMoviesDataBase.movieDao().insert(list)
            ResultAPI.OnSuccess(list)
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }

    override suspend fun saveTopRatedMovies(list: List<MovieModel>): ResultAPI<List<MovieModel>> {
        return try {
            topRatedMoviesDataBase.movieDao().insert(list)
            ResultAPI.OnSuccess(list)
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }

    override suspend fun getNowPlayingMovies(): ResultAPI<List<MovieModel>> {
        return try {
            ResultAPI.OnSuccess(nowPlayingMoviesDataBase.movieDao().getAll())
        } catch (e: Exception) {
            ResultAPI.OnFailure(e)
        }
    }

    override suspend fun getTopRatedMovies(): ResultAPI<List<MovieModel>> {
        return try {
            ResultAPI.OnSuccess(topRatedMoviesDataBase.movieDao().getAll())
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

    override suspend fun saveFavoriteIfNoExistMovies(
        list: List<MovieModel>,
    ): ResultAPI<List<MovieModel>> {
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

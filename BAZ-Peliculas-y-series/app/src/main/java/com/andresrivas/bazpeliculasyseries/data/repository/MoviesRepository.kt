package com.andresrivas.bazpeliculasyseries.data.repository

import com.andresrivas.bazpeliculasyseries.data.repository.datasource.MoviesDataSource
import com.andresrivas.bazpeliculasyseries.data.repository.datasource.MoviesLocalDataSource
import com.andresrivas.bazpeliculasyseries.domain.model.LatestMoviesModel
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesPagesModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpeliculasyseries.injection.MoviesRepositoryRemote
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    @MoviesRepositoryRemote val remoteDataSource: MoviesDataSource,
    private val localDataSource: MoviesLocalDataSource
) {

    fun getMoviesNowPlaying(): Flow<ResultAPI<MoviesPagesModel>> = flow {
        val playingNowMovies = remoteDataSource.getMoviesNowPlaying()
        when (playingNowMovies) {
            is ResultAPI.OnSuccess -> {
                savePlayingNowMoviesLocal(playingNowMovies.data.resultList)
            }
            else -> {}
        }
        emit(playingNowMovies)
    }

    fun getLatestMovies(): Flow<ResultAPI<LatestMoviesModel>> = flow {
        emit(remoteDataSource.getLatestMovies())
    }

    fun getMoviesTopRated(): Flow<ResultAPI<MoviesPagesModel>> = flow {
        emit(remoteDataSource.getMoviesTopRated())
    }

    fun getMoviesTrending(): Flow<ResultAPI<MoviesPagesModel>> = flow {
        emit(remoteDataSource.getMoviesTrending())
    }


    fun getMoviesVideo(movieId: String): Flow<ResultAPI<MoviesVideoModel>> = flow {
        emit(remoteDataSource.getMoviesVideo(movieId))
    }

    suspend fun savePlayingNowMoviesLocal(movies: List<MovieModel>): ResultAPI<List<MovieModel>> {
        return localDataSource.saveMoviesPlayingNow(movies)
    }

    fun saveFavorites(movies: List<MovieModel>): Flow<ResultAPI<List<MovieModel>>> = flow {
        emit(localDataSource.saveFavoriteMovies(movies))
    }

    fun saveFavoritesIfNoExist(movies: List<MovieModel>): Flow<ResultAPI<List<MovieModel>>> = flow {
        emit(localDataSource.saveFavoriteIfNoExistMovies(movies))
    }

    fun removeFavorites(movies: List<MovieModel>): Flow<ResultAPI<List<MovieModel>>> = flow {
        emit(localDataSource.removeFavoriteMovies(movies))
    }

    fun getFavorites(): Flow<ResultAPI<List<MovieModel>>> = flow {
        emit(localDataSource.getFavoriteMovies())
    }

    fun isFavorite(movie: MovieModel): Flow<ResultAPI<Boolean>> = flow {
        emit(localDataSource.isFavorite(movie))
    }
}

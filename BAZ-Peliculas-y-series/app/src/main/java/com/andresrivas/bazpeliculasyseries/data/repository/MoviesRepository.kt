package com.andresrivas.bazpeliculasyseries.data.repository

import com.andresrivas.bazpeliculasyseries.data.repository.datasource.MoviesDataSource
import com.andresrivas.bazpeliculasyseries.data.repository.datasource.MoviesLocalDataSource
import com.andresrivas.bazpeliculasyseries.domain.model.LatestMoviesModel
import com.andresrivas.bazpeliculasyseries.domain.model.MovieModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesPagesModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpeliculasyseries.injection.MoviesRepositoryRemote
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    @MoviesRepositoryRemote val remoteDataSource: MoviesDataSource,
    private val localDataSource: MoviesLocalDataSource,
) {

    fun getMoviesNowPlaying(): Flow<ResultAPI<MoviesPagesModel>> = flow {
        var playingNowMovies = remoteDataSource.getMoviesNowPlaying()
        when (playingNowMovies) {
            is ResultAPI.OnSuccess -> {
                savePlayingNowMoviesLocal(playingNowMovies.data.resultList)
            }
            else -> {
                when (val result = localDataSource.getNowPlayingMovies()) {
                    is ResultAPI.OnFailure -> {
                        playingNowMovies = ResultAPI.OnFailure(result.exception)
                    }
                    is ResultAPI.OnLoading -> {}
                    is ResultAPI.OnSuccess -> {
                        playingNowMovies =
                            ResultAPI.OnSuccess(MoviesPagesModel(resultList = result.data))
                    }
                }
            }
        }
        emit(playingNowMovies)
    }

    fun getLatestMovies(): Single<ResultAPI<LatestMoviesModel>> {
        return remoteDataSource.getLatestMovies()
    }

    fun getMoviesTopRated(): Flow<ResultAPI<MoviesPagesModel>> = flow {
        var topRatedMovies = remoteDataSource.getMoviesTopRated()
        when (topRatedMovies) {
            is ResultAPI.OnSuccess -> {
                saveTopRatedMoviesLocal(topRatedMovies.data.resultList)
            }
            else -> {
                when (val result = localDataSource.getTopRatedMovies()) {
                    is ResultAPI.OnFailure -> topRatedMovies = ResultAPI.OnFailure(result.exception)
                    is ResultAPI.OnLoading -> {}
                    is ResultAPI.OnSuccess -> {
                        topRatedMovies =
                            ResultAPI.OnSuccess(MoviesPagesModel(resultList = result.data))
                    }
                }
            }
        }
        emit(topRatedMovies)
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

    suspend fun saveTopRatedMoviesLocal(movies: List<MovieModel>): ResultAPI<List<MovieModel>> {
        return localDataSource.saveTopRatedMovies(movies)
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

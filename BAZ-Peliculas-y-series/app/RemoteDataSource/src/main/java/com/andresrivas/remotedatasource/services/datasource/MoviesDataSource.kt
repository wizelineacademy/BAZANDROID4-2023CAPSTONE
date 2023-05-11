package com.andresrivas.remotedatasource.services.datasource

import com.andresrivas.bazpeliculasyseries.domain.model.LatestMoviesModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesPagesModel
import com.andresrivas.common.model.MoviesVideoModel
import com.andresrivas.common.utilities.ResultAPI
import io.reactivex.rxjava3.core.Single

interface MoviesDataSource {
    suspend fun getMoviesNowPlaying(): ResultAPI<MoviesPagesModel>
    suspend fun getMoviesTrending(): ResultAPI<MoviesPagesModel>
    suspend fun getMoviesTopRated(): ResultAPI<MoviesPagesModel>
    suspend fun getMoviesVideo(movieId: String): ResultAPI<MoviesVideoModel>

    fun getLatestMovies(): Single<ResultAPI<LatestMoviesModel>>
}

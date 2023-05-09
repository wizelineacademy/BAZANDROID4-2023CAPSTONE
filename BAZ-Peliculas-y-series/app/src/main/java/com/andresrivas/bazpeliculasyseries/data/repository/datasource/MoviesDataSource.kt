package com.andresrivas.bazpeliculasyseries.data.repository.datasource

import com.andresrivas.bazpeliculasyseries.domain.model.LatestMoviesModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesPagesModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import io.reactivex.rxjava3.core.Single

interface MoviesDataSource {
    suspend fun getMoviesNowPlaying(): ResultAPI<MoviesPagesModel>
    suspend fun getMoviesTrending(): ResultAPI<MoviesPagesModel>
    suspend fun getMoviesTopRated(): ResultAPI<MoviesPagesModel>
    suspend fun getMoviesVideo(movieId: String): ResultAPI<MoviesVideoModel>

    fun getLatestMovies(): Single<ResultAPI<LatestMoviesModel>>
}

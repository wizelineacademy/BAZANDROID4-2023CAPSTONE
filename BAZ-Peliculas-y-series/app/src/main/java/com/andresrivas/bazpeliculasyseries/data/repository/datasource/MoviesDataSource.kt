package com.andresrivas.bazpeliculasyseries.data.repository.datasource

import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesPagesModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI

interface MoviesDataSource {
    suspend fun getMoviesPlayingNow(): ResultAPI<MoviesPagesModel>
    suspend fun getMoviesTrending(): ResultAPI<MoviesPagesModel>
    suspend fun getMoviesVideo(movieId: String): ResultAPI<MoviesVideoModel>
}
package mx.jossprogramming.jlmovieswizel.common.utils.fakes

import javax.inject.Inject
import mx.jossprogramming.remote.models.DetailMovies
import mx.jossprogramming.remote.models.GenresResponse
import mx.jossprogramming.remote.models.MovieDbResponse
import mx.jossprogramming.remote.network.MovieApiClient

class FakeMovieApiClient @Inject constructor() : MovieApiClient {
    override suspend fun getListPlayingNow(page: Int): MovieDbResponse {
        return MoviesFakes.MovieResponseFake
    }

    override suspend fun getListTopRated(page: Int): MovieDbResponse {
        return MoviesFakes.MovieResponseFake
    }

    override suspend fun getLatest(): DetailMovies {
        return MoviesFakes.MovieResponseFake.results.first()
    }

    override suspend fun getLisyPopular(page: Int): MovieDbResponse {
        return MoviesFakes.MovieResponseFake
    }

    override suspend fun getGenreList(): GenresResponse {
        return GenresResponse(genres = emptyList())
    }
}
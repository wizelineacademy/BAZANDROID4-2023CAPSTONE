package mx.jossprogramming.jlmovieswizel.domain

import mx.jossprogramming.remote.models.DetailMovies
import mx.jossprogramming.remote.network.MovieApiClient
import javax.inject.Inject

class GetLatestMovieUseCase @Inject constructor(private val api: MovieApiClient) {
    suspend operator fun invoke(): DetailMovies {
        return api.getLatest()
    }
}
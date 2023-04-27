package mx.jossprogramming.jlmovieswizel.domain

import javax.inject.Inject
import mx.jossprogramming.remote.models.DetailMovies
import mx.jossprogramming.remote.network.MovieApiClient

class GetLatestMovieUseCase @Inject constructor(private val api: MovieApiClient) {
    suspend operator fun invoke(): DetailMovies {
        return api.getLatest()
    }
}
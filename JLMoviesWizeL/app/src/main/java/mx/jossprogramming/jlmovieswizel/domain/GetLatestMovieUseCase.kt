package mx.jossprogramming.jlmovieswizel.domain

import mx.jossprogramming.jlmovieswizel.data.models.DetailMovies
import mx.jossprogramming.jlmovieswizel.data.network.MovieApiClient
import javax.inject.Inject

class GetLatestMovieUseCase @Inject constructor(private val api: MovieApiClient) {
    suspend operator fun invoke(): DetailMovies {
        return api.getLatest()
    }
}
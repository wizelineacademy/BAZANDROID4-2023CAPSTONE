package mx.jossprogramming.jlmovieswizel.domain

import javax.inject.Inject
import mx.jossprogramming.remote.models.GenresResponse
import mx.jossprogramming.remote.network.MovieApiClient

class GetGenresListUseCase @Inject constructor(private val api: MovieApiClient) {
    suspend operator fun invoke(): GenresResponse {
        return api.getGenreList()
    }
}
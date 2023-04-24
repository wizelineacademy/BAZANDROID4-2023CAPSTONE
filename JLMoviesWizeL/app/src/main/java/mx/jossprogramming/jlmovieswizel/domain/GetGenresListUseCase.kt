package mx.jossprogramming.jlmovieswizel.domain

import mx.jossprogramming.remote.models.GenresResponse
import mx.jossprogramming.remote.network.MovieApiClient
import javax.inject.Inject

class GetGenresListUseCase @Inject constructor(private val api: MovieApiClient) {
    suspend operator fun invoke(): GenresResponse {
        return api.getGenreList()
    }
}
package mx.jossprogramming.jlmovieswizel.domain

import mx.jossprogramming.jlmovieswizel.data.models.GenresResponse
import mx.jossprogramming.jlmovieswizel.data.network.MovieApiClient
import javax.inject.Inject

class GetGenresListUseCase @Inject constructor(private val api: MovieApiClient) {
    suspend operator fun invoke(): GenresResponse {
        return api.getGenreList()
    }
}
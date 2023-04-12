package mx.jossprogramming.jlmovieswizel.domain

import mx.jossprogramming.jlmovieswizel.common.Constantes
import mx.jossprogramming.jlmovieswizel.data.models.MovieDbResponse
import mx.jossprogramming.jlmovieswizel.data.network.MovieApiClient
import javax.inject.Inject

class GetMoviesTopRatedUseCase @Inject constructor(private val api: MovieApiClient) {
    suspend operator fun invoke(): MovieDbResponse {
        return api.getListTopRated(Constantes.PAGE)
    }
}
package mx.jossprogramming.jlmovieswizel.domain

import mx.jossprogramming.jlmovieswizel.common.Constantes
import mx.jossprogramming.jlmovieswizel.data.models.MovieDbResponse
import mx.jossprogramming.jlmovieswizel.data.network.MovieApiClient
import javax.inject.Inject

class GetMoviesNowPlayingUseCase @Inject constructor(private val api: MovieApiClient) {
    suspend operator fun invoke(): MovieDbResponse {
        return api.getListPlayingNow(Constantes.PAGE)
    }
}
package mx.jossprogramming.jlmovieswizel.domain

import javax.inject.Inject
import mx.jossprogramming.jlmovieswizel.common.Constantes
import mx.jossprogramming.remote.models.MovieDbResponse
import mx.jossprogramming.remote.network.MovieApiClient

class GetMoviesNowPlayingUseCase @Inject constructor(private val api: MovieApiClient) {
    suspend operator fun invoke(): MovieDbResponse {
        return api.getListPlayingNow(Constantes.PAGE)
    }
}
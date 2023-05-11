package com.andresrivas.bazpeliculasyseries.domain.usecases

import com.andresrivas.bazpeliculasyseries.common.domain.UseCase
import com.andresrivas.bazpeliculasyseries.data.repository.MoviesRepository
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesPagesModel
import com.andresrivas.common.utilities.ResultAPI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNowPlayingUseCase @Inject constructor(
    private val playingNowRepository: MoviesRepository,
) : UseCase<Nothing, MoviesPagesModel> {
    override fun execute(params: Nothing?): Flow<ResultAPI<MoviesPagesModel>> {
        return playingNowRepository.getMoviesNowPlaying()
    }
}

package com.example.themoviedb.domain.usecase

import com.example.themoviedb.domain.MovieRepository
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
): BaseUseCase<Unit> {

    override suspend fun execute() {
        repository.getNowPlaying()
    }
}
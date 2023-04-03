package com.example.themoviedb.domain.usecase

import com.example.themoviedb.domain.MovieModel
import com.example.themoviedb.domain.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveNowPlayingUseCase @Inject  constructor(
    private val repository: MovieRepository
): FlowBaseUseCase<Flow<List<MovieModel>>> {

    override fun execute(): Flow<List<MovieModel>> {
        return repository.getNowPlayingStream()
    }
}
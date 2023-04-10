package com.andresrivas.bazpeliculasyseries.domain.usecases

import com.andresrivas.bazpeliculasyseries.data.repository.MoviesRepository
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetPlayingNowUseCaseTest {

    @RelaxedMockK
    private lateinit var playingNowRepository: MoviesRepository
    private lateinit var getPlayingNowUseCase: GetPlayingNowUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPlayingNowUseCase = GetPlayingNowUseCase(playingNowRepository)
    }

    @Test
    fun `when api doesn't return something use case will be return OnFailure`() = runBlocking {
        //Given
        coEvery { playingNowRepository.getMoviesPlayingNow() } returns flow { ResultAPI.OnFailure(Exception()) }
        //When
        getPlayingNowUseCase.execute()
        //Then
        coVerify(exactly = 1) { playingNowRepository.getMoviesPlayingNow() }
    }
}
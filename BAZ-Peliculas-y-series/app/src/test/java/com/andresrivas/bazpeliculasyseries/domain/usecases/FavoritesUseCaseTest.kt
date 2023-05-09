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

internal class FavoritesUseCaseTest {

    @RelaxedMockK
    private lateinit var moviesRepository: MoviesRepository
    private lateinit var favoritesUseCase: FavoritesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        favoritesUseCase = FavoritesUseCase(moviesRepository)
    }

    @Test
    fun `when api doesn't return something use case will be return OnFailure`() = runBlocking {
        // Given
        coEvery { moviesRepository.getMoviesNowPlaying() } returns flow {
            ResultAPI.OnFailure(Exception())
        }
        // When
        favoritesUseCase.execute()
        // Then
        coVerify(exactly = 1) { moviesRepository.getMoviesNowPlaying() }
    }

    @Test
    fun `Given null params When use case is called Then return OnFailure for null params`() =
        runBlocking {
            // Given
            // coEvery { moviesRepository.getMoviesVideo("") } returns flow { ResultAPI.OnFailure(Exception()) }
            // When
            favoritesUseCase.execute()
            // Then
            coVerify(exactly = 1) { ResultAPI.OnFailure(NullPointerException()) }
        }
}

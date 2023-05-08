package com.example.themoviedb.domain.usecase

import com.example.core.model.MovieModel
import com.example.themoviedb.domain.MovieRepository
import com.example.themoviedb.presentation.MockTest.movies
import com.example.tools.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetTopRatedMoviesUseCaseTest {
    @MockK
    private lateinit var repository: MovieRepository

    private lateinit var useCase: GetTopRatedMoviesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        useCase = GetTopRatedMoviesUseCase(repository)
    }

    @Test
    fun `use case test getTopRatedMovies when repository returns success response`() = runBlocking {

        coEvery { repository.getTopRated() } returns flowOf(ResultWrapper.Success(movies))

        val resultFlow = useCase.execute()

        resultFlow.collect { result ->
            assertTrue(result is ResultWrapper.Success)
            assertEquals(movies, (result as ResultWrapper.Success).data)
        }

        coVerify(exactly = 1) { repository.getTopRated() }
    }

    @Test
    fun `use case test getTopRatedMovies when repository returns error response`() = runBlocking {
        coEvery {
            repository.getTopRated()
        } returns flowOf(ResultWrapper.Error(0, "Something went wrong"))

        val resultFlow = useCase.execute()

        resultFlow.collect { result ->
            assertTrue(result is ResultWrapper.Error)
            assertEquals(0, (result as ResultWrapper.Error).code)
            assertEquals("Something went wrong", result.message)
        }

        coVerify(exactly = 1) { repository.getTopRated() }
    }
}

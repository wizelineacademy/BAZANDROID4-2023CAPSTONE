package com.example.themoviedb.domain.usecase

import com.example.core.model.MovieModel
import com.example.themoviedb.domain.MovieRepository
import com.example.themoviedb.presentation.MockTest
import com.example.themoviedb.presentation.MockTest.movie
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

class GetLatestMovieUseCaseTest {
    @MockK
    private lateinit var repository: MovieRepository

    private lateinit var useCase: GetLatestMovieUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        useCase = GetLatestMovieUseCase(repository)
    }

    @Test
    fun `use case test getLatestMovie when repository returns success response`() = runBlocking {

        coEvery { repository.getLatest() } returns flowOf(ResultWrapper.Success(movie))

        val resultFlow = useCase.execute()

        resultFlow.collect { result ->
            assertTrue(result is ResultWrapper.Success)
            assertEquals(movie, (result as ResultWrapper.Success).data)
        }

        coVerify(exactly = 1) { repository.getLatest() }
    }

    @Test
    fun `use case test getLatestMovie when repository returns error response`() = runBlocking {
        coEvery {
            repository.getLatest()
        } returns flowOf(ResultWrapper.Error(0, "Something went wrong"))

        val resultFlow = useCase.execute()

        resultFlow.collect { result ->
            assertTrue(result is ResultWrapper.Error)
            assertEquals(0, (result as ResultWrapper.Error).code)
            assertEquals("Something went wrong", result.message)
        }

        coVerify(exactly = 1) { repository.getLatest() }
    }
}

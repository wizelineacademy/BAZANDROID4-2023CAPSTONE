package com.example.themoviedb.domain.usecase

import com.example.core.model.GenreModel
import com.example.themoviedb.domain.MovieRepository
import com.example.themoviedb.presentation.MockTest
import com.example.tools.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetGenresMovieUseCaseTest {
    @MockK
    private lateinit var repository: MovieRepository

    private lateinit var useCase: GetGenresMovieUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        useCase = GetGenresMovieUseCase(repository)
    }

    @Test
    fun `use case test getGenresMovie when repository returns success response`() = runBlocking {
        coEvery { repository.getGenresMovie(MockTest.ids) } returns ResultWrapper.Success(MockTest.genres)

        val result = useCase.execute(MockTest.ids)
        assertTrue(result is ResultWrapper.Success)
        assertEquals(MockTest.genres, (result as ResultWrapper.Success).data)

        coVerify(exactly = 1) { repository.getGenresMovie(MockTest.ids) }
    }

    @Test
    fun `use case test getGenresMovie when repository returns error response`() = runBlocking {
        coEvery {
            repository.getGenresMovie(MockTest.ids)
        } returns ResultWrapper.Error(0, "Something went wrong")

        val result = useCase.execute(MockTest.ids)
        assertTrue(result is ResultWrapper.Error)
        assertEquals(0, (result as ResultWrapper.Error).code)
        assertEquals("Something went wrong", result.message)

        coVerify(exactly = 1) { repository.getGenresMovie(MockTest.ids) }
    }
}

package com.example.remote

import com.example.remote.mapper.transformToDomain
import com.example.remote.response.GenresResponse
import com.example.remote.response.MovieResponse
import com.example.remote.service.MovieApiService
import com.example.tools.ResultWrapper
import com.example.tools.fake.MockData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class MovieRemoteDataSourceImplTest {

    @RelaxedMockK
    private lateinit var apiService: MovieApiService

    private lateinit var remoteDataSource: MovieRemoteDataSourceImpl

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        remoteDataSource = MovieRemoteDataSourceImpl(apiService)
    }

    @Test
    fun `callNowPlayingMovies should return success result with now playing movies`() = runBlocking {
        val moviesResponse = MockData.gson.fromJson(
            MockData.moviesResponse,
            MovieResponse::class.java
        )
        coEvery { apiService.getNowPlayingMovies() } returns moviesResponse

        val result = remoteDataSource.callNowPlayingMovies()

        assertTrue(result is ResultWrapper.Success)
        assertEquals(moviesResponse.transformToDomain(), (result as ResultWrapper.Success).data)
        coVerify(exactly = 1) { apiService.getNowPlayingMovies() }
    }

    @Test
    fun `callTopRatedMovies should return success result with top rated movies`() = runBlocking {
        val moviesResponse = MockData.gson.fromJson(
            MockData.moviesResponse,
            MovieResponse::class.java
        )
        coEvery { apiService.getTopRatedMovies() } returns moviesResponse

        val result = remoteDataSource.callTopRatedMovies()

        assertTrue(result is ResultWrapper.Success)
        assertEquals(moviesResponse.transformToDomain(), (result as ResultWrapper.Success).data)
        coVerify(exactly = 1) { apiService.getTopRatedMovies() }
    }

    @Test
    fun `callGenresMovies should return success result with genres`() = runBlocking {
        val genresResponse = MockData.gson.fromJson(
            MockData.genresResponse,
            GenresResponse::class.java
        )
        coEvery { apiService.getGenresMovies() } returns genresResponse

        val result = remoteDataSource.callGenresMovies()

        assertTrue(result is ResultWrapper.Success)
        assertEquals(genresResponse.transformToDomain(), (result as ResultWrapper.Success).data)
        coVerify(exactly = 1) { apiService.getGenresMovies() }
    }
}
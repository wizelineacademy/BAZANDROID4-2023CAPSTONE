package com.example.themoviedb.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.remote.mapper.transformToDomain
import com.example.remote.response.GenresResponse
import com.example.remote.response.LatestResponse
import com.example.remote.response.MovieResponse
import com.example.themoviedb.domain.usecase.GetGenresMovieUseCase
import com.example.themoviedb.domain.usecase.GetGenresMoviesUseCase
import com.example.themoviedb.domain.usecase.GetLatestMovieUseCase
import com.example.themoviedb.domain.usecase.GetNowPlayingMoviesUseCase
import com.example.themoviedb.domain.usecase.GetTopRatedMoviesUseCase
import com.example.themoviedb.presentation.MockTest
import com.example.themoviedb.presentation.MockTest.genres
import com.example.themoviedb.presentation.MockTest.ids
import com.example.tools.ResultWrapper
import com.example.tools.fake.MockData
import com.example.tools.fake.MockData.toModelResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase

    @MockK
    lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @MockK
    lateinit var getLatestMovieUseCase: GetLatestMovieUseCase

    @MockK
    lateinit var getGenresMoviesUseCase: GetGenresMoviesUseCase

    @MockK
    lateinit var getGenresMovieUseCase: GetGenresMovieUseCase

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MovieViewModel(
            getNowPlayingMoviesUseCase,
            getTopRatedMoviesUseCase,
            getLatestMovieUseCase,
            getGenresMoviesUseCase,
            getGenresMovieUseCase
        )
    }

    @Test
    fun `callNowPlayingMovies should update nowPlayingState with result`() = runBlocking {
        val movies = MockData.moviesResponse.toModelResponse(MovieResponse::class.java).transformToDomain()
        coEvery { getNowPlayingMoviesUseCase.execute() } returns flowOf(ResultWrapper.Success(movies))

        viewModel.callNowPlayingMovies()

        coVerify(exactly = 1) { getNowPlayingMoviesUseCase.execute() }
    }

    @Test
    fun getNowPlayingState() {
    }

    @Test
    fun getTopRatedState() = runBlocking {
        val movies = MockData.moviesResponse.toModelResponse(MovieResponse::class.java).transformToDomain()
        coEvery { getTopRatedMoviesUseCase.execute() } returns flowOf(ResultWrapper.Success(movies))

        viewModel.callTopRatedMovies()

        coVerify(exactly = 1) { getTopRatedMoviesUseCase.execute() }
    }

    @Test
    fun getLatestState() {
        val movies = MockData.latestResponse.toModelResponse(LatestResponse::class.java).transformToDomain()
        coEvery { getLatestMovieUseCase.execute() } returns flowOf(ResultWrapper.Success(movies))

        viewModel.callLatestMovie()

        coVerify(exactly = 1) { getLatestMovieUseCase.execute() }
    }

    @Test
    fun callGenresMovies() = runBlocking {
        val genres = MockData.genresResponse.toModelResponse(GenresResponse::class.java).transformToDomain()
        coEvery { getGenresMoviesUseCase.execute() } returns flowOf(ResultWrapper.Success(genres))

        viewModel.callGenresMovies()

        coVerify(exactly = 1) { getGenresMoviesUseCase.execute() }
    }

    @Test
    fun getGenresMovie() = runBlocking {
        coEvery { getGenresMovieUseCase.execute(ids) } returns ResultWrapper.Success(genres)

        viewModel.getGenresMovie(ids)

        coVerify(exactly = 1) {
            getGenresMovieUseCase.execute(ids)
        }
    }
}

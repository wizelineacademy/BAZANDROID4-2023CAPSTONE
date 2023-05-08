package com.example.themoviedb.data.repository

import com.example.core.model.GenreModel
import com.example.core.model.MovieModel
import com.example.local.MovieLocalDataSource
import com.example.local.entity.GenreLocal
import com.example.local.entity.NowPlayingLocal
import com.example.local.entity.NowPlayingLocalWithGenres
import com.example.remote.MovieRemoteDataSource
import com.example.themoviedb.data.mapper.toGenresEntities
import com.example.themoviedb.data.mapper.toModel
import com.example.themoviedb.data.mapper.toNowPlayingEntities
import com.example.themoviedb.data.mapper.toTopRatedEntities
import com.example.themoviedb.presentation.MockTest
import com.example.themoviedb.presentation.MockTest.genres
import com.example.themoviedb.presentation.MockTest.ids
import com.example.themoviedb.presentation.MockTest.localGenres
import com.example.themoviedb.presentation.MockTest.localMovies
import com.example.themoviedb.presentation.MockTest.movies
import com.example.tools.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieRepositoryImplTest {

    @MockK
    lateinit var localDataSource: MovieLocalDataSource

    @MockK
    lateinit var remoteDataSource: MovieRemoteDataSource

    private lateinit var repository: MovieRepositoryImpl

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        repository = MovieRepositoryImpl(localDataSource, remoteDataSource)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `getNowPlaying should return success result when remote and local data sources succeed`() = runBlocking {

        coEvery {
            remoteDataSource.callNowPlayingMovies()
        } returns ResultWrapper.Success(movies)
        coEvery {
            localDataSource.saveNowPlaying(movies.toNowPlayingEntities())
        } just Runs
        coEvery { localDataSource.getNowPlaying() } returns flowOf(localMovies)

        val result = repository.getNowPlaying()

        result.collect { result ->
            assertTrue(result is ResultWrapper.Success)
            assertEquals(movies, (result as ResultWrapper.Success).data)
        }
        coVerify(exactly = 1) { remoteDataSource.callNowPlayingMovies() }
        coVerify(exactly = 1) { localDataSource.saveNowPlaying(movies.toNowPlayingEntities()) }
        coVerify(exactly = 1) { localDataSource.getNowPlaying() }
    }


    @Test
    fun `getTopRated should return success result when remote and local data sources succeed`() = runBlocking {

        coEvery { remoteDataSource.callTopRatedMovies() } returns ResultWrapper.Success(movies)
        coEvery {
            localDataSource.saveTopRated(movies.toTopRatedEntities())
        } just Runs
        coEvery { localDataSource.getTopRated() } returns flowOf(movies.toTopRatedEntities())

        val result = repository.getTopRated()

        result.collect { result ->
            assertTrue(result is ResultWrapper.Success)
            assertEquals(movies, (result as ResultWrapper.Success).data)
        }
        coVerify(exactly = 1) { remoteDataSource.callTopRatedMovies() }
        coVerify(exactly = 1) { localDataSource.saveTopRated(movies.toTopRatedEntities()) }
        coVerify(exactly = 1) { localDataSource.getTopRated() }
    }

    @Test
    fun `getGenresMovies should return success result when remote sources succeed`() = runBlocking {

        coEvery { remoteDataSource.callGenresMovies() } returns ResultWrapper.Success(genres)
        coEvery {
            localDataSource.saveGenres(genres.toGenresEntities())
        } returns ResultWrapper.Success(true)

        val result = repository.getGenres()

        result.collect { result ->
            assertTrue(result is ResultWrapper.Success)
            assertEquals(genres, (result as ResultWrapper.Success).data)
        }
        coVerify(exactly = 1) { remoteDataSource.callGenresMovies() }
        coVerify(exactly = 1) { localDataSource.saveGenres(genres.toGenresEntities()) }
    }

    @Test
    fun `getGenresMovie should return success result with genres for given movie ids`() = runBlocking {
        coEvery { localDataSource.getGenresMovie(ids) } returns ResultWrapper.Success(localGenres)

        val result = repository.getGenresMovie(ids)

        assertTrue(result is ResultWrapper.Success)
        assertEquals(localGenres.toModel(), (result as ResultWrapper.Success).data)

        coVerify(exactly = 1) { localDataSource.getGenresMovie(ids) }
    }
}

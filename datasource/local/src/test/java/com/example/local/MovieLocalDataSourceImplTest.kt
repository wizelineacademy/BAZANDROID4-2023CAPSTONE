package com.example.local

import com.example.local.dao.GenresDao
import com.example.local.dao.LatestDao
import com.example.local.dao.NowPlayingDao
import com.example.local.dao.TopRatedDao
import com.example.local.entity.GenreLocal
import com.example.local.entity.NowPlayingLocal
import com.example.local.entity.NowPlayingLocalWithGenres
import com.example.local.entity.TopRatedLocal
import com.example.tools.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class MovieLocalDataSourceImplTest {

    @MockK
    lateinit var nowPlayingDao: NowPlayingDao

    @MockK
    lateinit var latestDao: LatestDao

    @MockK
    lateinit var topRatedDao: TopRatedDao

    @MockK
    lateinit var genresDao: GenresDao

    private lateinit var localDataSource: MovieLocalDataSource

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        localDataSource = MovieLocalDataSourceImpl(
            nowPlayingDao,
            latestDao,
            topRatedDao,
            genresDao
        )
    }

    @Test
    fun `should return all "now playing" movies from the local data source`() = runBlocking {
        val localMovies = listOf(
            NowPlayingLocalWithGenres(
                NowPlayingLocal(
                    id = 1,
                    title = "Movie 1",
                    description = "",
                    posterPath = "",
                    genreIds = emptyList()
                ),
                genres = emptyList()
            ),
            NowPlayingLocalWithGenres(
                NowPlayingLocal(
                    id = 2,
                    title = "Movie 2",
                    description = "",
                    posterPath = "",
                    genreIds = emptyList()
                ),
                genres = emptyList()
            )
        )

        coEvery {
            nowPlayingDao.observeAll()
        } returns flowOf(localMovies)

        val result = localDataSource.getNowPlaying()

        result.collect {
            assertEquals(localMovies, it)
        }

        coVerify(exactly = 1) { localDataSource.getNowPlaying() }
    }

    @Test
    fun `should return all "top rated" movies from the local data source`() = runBlocking {
        val localMovies = listOf(
            TopRatedLocal(
                id = 1,
                title = "Movie 1",
                description = "",
                posterPath = "",
                genreIds = emptyList()
            ),
            TopRatedLocal(
                id = 2,
                title = "Movie 2",
                description = "",
                posterPath = "",
                genreIds = emptyList()
            )
        )

        coEvery {
            topRatedDao.observeAll()
        } returns flowOf(localMovies)

        val result = localDataSource.getTopRated()

        result.collect {
            assertEquals(localMovies, it)
        }
        coVerify(exactly = 1) { localDataSource.getTopRated() }
    }

    @Test
    fun `should save a list of "now playing" movies to the local data source`() = runBlocking {
        val localMovies = listOf(
            NowPlayingLocal(
                id = 1,
                title = "Movie 1",
                description = "",
                posterPath = "",
                genreIds = emptyList()
            ),
            NowPlayingLocal(
                id = 2,
                title = "Movie 2",
                description = "",
                posterPath = "",
                genreIds = emptyList()
            )
        )

        coEvery { nowPlayingDao.insertAll(localMovies) } just Runs

        localDataSource.saveNowPlaying(localMovies)
        coVerify(exactly = 1) { nowPlayingDao.insertAll(localMovies) }
    }

    @Test
    fun `should save a list of "top rated" movies to the local data source`() = runBlocking {
        val localMovies = listOf(
            TopRatedLocal(
                id = 1,
                title = "Movie 1",
                description = "",
                posterPath = "",
                genreIds = emptyList()
            ),
            TopRatedLocal(
                id = 2,
                title = "Movie 2",
                description = "",
                posterPath = "",
                genreIds = emptyList()
            )
        )

        coEvery { topRatedDao.insertAll(localMovies) } just Runs

        localDataSource.saveTopRated(localMovies)
        coVerify(exactly = 1) { topRatedDao.insertAll(localMovies) }
    }

    @Test
    fun `should save a list of movie genres to the local data source`() = runBlocking {
        val genres = listOf(
            GenreLocal(name = "fantasy"),
            GenreLocal(name = "action")
        )

        coEvery { genresDao.insertAll(genres) } just Runs

        localDataSource.saveGenres(genres)

        coVerify(exactly = 1) { genresDao.insertAll(genres) }
    }

    @Test
    fun `should return the genres of a list of movies from the local data source`() = runBlocking {
        val ids = listOf(1, 2)
        val genres = listOf(
            GenreLocal(name = "fantasy"),
            GenreLocal(name = "action")
        )

        coEvery { genresDao.getGenres(ids) } returns genres

        val result = localDataSource.getGenresMovie(ids)
        assertTrue(result is ResultWrapper.Success)
        assertEquals(genres, (result as ResultWrapper.Success).data)

        coVerify(exactly = 1) { genresDao.getGenres(ids) }
    }
}
package mx.jossprogramming.jlmovieswizel

import kotlinx.coroutines.test.runTest
import mx.jossprogramming.jlmovieswizel.common.utils.fakes.FakeMovieApiClient
import mx.jossprogramming.jlmovieswizel.domain.GetMoviesNowPlayingUseCase
import mx.jossprogramming.jlmovieswizel.domain.GetMoviesTopRatedUseCase
import org.junit.Assert
import org.junit.Test

class MoviesUnitTest {
    @Test
    fun get_movies_now_playing_contains_data() = runTest {
        val useCase = GetMoviesNowPlayingUseCase(FakeMovieApiClient())
        val data = useCase.invoke().results
        Assert.assertEquals(true, data.isNotEmpty())
    }

    @Test
    fun get_movies_top_rated_contains_data() = runTest {
        val useCase = GetMoviesTopRatedUseCase(FakeMovieApiClient())
        val data = useCase.invoke().results
        Assert.assertEquals(true, data.isNotEmpty())
    }
}
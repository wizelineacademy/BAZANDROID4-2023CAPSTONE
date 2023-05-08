package mx.jramon.subias.dbmovieproyect.movies.viewmodel

import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieEntity
import mx.jramon.subias.dbmovieproyect.movies.domain.useCase.GetDetailsMovieUseCase
import mx.jramon.subias.dbmovieproyect.movies.domain.useCase.GetMoviePopularUseCase
import mx.jramon.subias.dbmovieproyect.movies.domain.useCase.GetPopularTvSeriesUseCase
import mx.jramon.subias.dbmovieproyect.movies.domain.useCase.GetRatedMoviesUseCase
import org.junit.Before
import org.junit.Test


class MovieViewModelTest {

    @RelaxedMockK
    private lateinit var getPopularMovieUseCase: GetMoviePopularUseCase
    private lateinit var getPopularTvSeriesUseCase: GetPopularTvSeriesUseCase
    private lateinit var getRatedMoviesUseCase: GetRatedMoviesUseCase
    private lateinit var getDetailsMovieUseCase: GetDetailsMovieUseCase
    private lateinit var vModel:MovieViewModel


    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        vModel = MovieViewModel(
            getPopularMovieUseCase,
            getPopularTvSeriesUseCase,
            getRatedMoviesUseCase,
            getDetailsMovieUseCase
        )
    }

    @Test()
    fun getListPopularMovie() = runTest {
        //Given
        val fakeData = listOf(
            MovieEntity(
                id = 640146,
                posterPath = "/ngl2FKBlU4fhbdsrtdom9LVLBXw.jpg",
                adult = false,
                overview = "Super-Hero partners Scott Lang and Hope van Dyne, along with with Hope's parents Janet van Dyne and Hank Pym, and Scott's daughter Cassie Lang, find themselves exploring the Quantum Realm, interacting with strange new creatures and embarking on an adventure that will push them beyond the limits of what they thought possible.",
                releaseDate = "2023-02-15",
                originalTitle = "Ant-Man and the Wasp: Quantumania",
                language = "en",
                title = "Ant-Man and the Wasp: Quantumania",
                backdropPaht = "/3CxUndGhUcZdt1Zggjdb2HkLLQX.jpg",
                popularity = 6051.035,
                voteCount = 2271,
                video = false,
                voteAverage = 6.6,
            )
        )

        coEvery { getPopularMovieUseCase.invoke(1) } returns Single.just(fakeData)

        //When
        vModel.getListPopularMovies()

    }

}
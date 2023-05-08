package mx.jramon.subias.dbmovieproyect.movies.data.repositories

import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.rx3.rxSingle
import kotlinx.coroutines.test.runTest
import mx.jramon.subias.dbmovieproyect.movies.data.dataSource.local.MovieLocalDataSource
import mx.jramon.subias.dbmovieproyect.movies.data.dataSource.remote.MovieRemoteDataSource
import mx.jramon.subias.dbmovieproyect.movies.domain.model.*
import mx.jramon.subias.dbmovieproyect.network.NetworkMonitor
import org.junit.Before
import org.junit.Test

class MovieRepositoryImplTest {

    private lateinit var repositoryImpl: MovieRepositoryImpl

    @RelaxedMockK
    private lateinit var movieRemoteDataSource: MovieRemoteDataSource

    @RelaxedMockK
    private lateinit var movieLocalDataSource: MovieLocalDataSource

    @RelaxedMockK
    private lateinit var networkMonitor:NetworkMonitor

    private val movieListFake =
        MovieList(
            pages = 1,
            results = listOf(
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
            ),
            totalResults = 763593,
            totalPages = 38180
        )

    private val movieDetailsFake =
        MovieDetails(
                    adult = false,
                    backdropPath = "/3CxUndGhUcZdt1Zggjdb2HkLLQX.jpg",
                    belongsToCollection = null,
                    budget = 200000000,
                    genres = listOf(Genres(id = 28, name = "Acción")),
                    homepage = "https://disneylatino.com/peliculas/ant-man-and-the-wasp-quantumania",
                    id = 640146,
                    imdb_id = "tt10954600",
                    original_language = "en",
                    original_title = "Ant-Man and the Wasp: Quantumania",
                    overview = "Los superhéroes Scott Lang y Hope van Dyne regresan para continuar sus aventuras como Ant-Man y Wasp. Con los padres de Hope, Hank Pym y Janet van Dyne, y con la hija de Scott, Cassie Lang, la familia explora el Reino Quántico, interactúa con extrañas criaturas y se embarca en una aventura que los hará ir más allá de lo que creían posible.",
                    popularity =  5569.327,
                    poster_path = "/jTNYlTEijZ6c8Mn4gvINOeB2HWM.jpg",
                    production_companies = listOf(CompaniesProduction(id = 420, logoPath ="/hUzeosd33nzE5MCNsZxCGEKTXaQ.png", name = "Marvel Studios", originCountry = "US" )),
                    production_countries = listOf(CountriesProduction(iso = "US", id ="" )) ,
                    release_date = "2023-02-15",
                    revenue = 464566092,
                    runtime = 125 ,
                    spoken_languages = listOf(SpokenLanguages(id = "English", iso = "en")) ,
                    status = "Released",
                    tagline = "Sé testigo del comienzo de una nueva dinastía.",
                    title = "Ant-Man and the Wasp: Quantumania",
                    video =false ,
                    vote_average = 6.571f,
                    vote_count = 2267,
        )


    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        repositoryImpl =
            MovieRepositoryImpl(
                movieRemoteDataSource = movieRemoteDataSource,
                movieLocalDataSource = movieLocalDataSource,
                networkMonitor = networkMonitor
            )
    }

    @Test
    fun getMovies_when_networkIsAvailable_return_remoteData() = runTest {
        coEvery { networkMonitor.isOnline() } returns true
        coEvery { movieRemoteDataSource.getPopularMovie(1) } returns rxSingle { movieListFake }

        //When
        val result = repositoryImpl.getPopularMovies(1)

        //Then
       assertThat(result).isEqualTo(movieRemoteDataSource.getPopularMovie(1).map { it.results }.subscribeOn(Schedulers.io()))
    }

    @Test
    fun getMovies_networkIsNotAvailable_insert_dataInRoom() = runTest {
        coEvery { networkMonitor.isOnline() } returns true
        val movieListEntity = movieListFake.results
        coEvery { movieRemoteDataSource.getPopularMovie(1) } returns rxSingle { movieListFake }

        //When
        repositoryImpl.getPopularMovies(1)

        //then
        coEvery { movieLocalDataSource.insertMovieEntity(movieListEntity) }
    }

    @Test
    fun getDetail_netWorkIsAvailable() = runTest {
        //given
        coEvery { networkMonitor.isOnline() } returns true
        val movieDetails = movieDetailsFake
        coEvery { movieRemoteDataSource.getDetailMovie(640146) } returns rxSingle { movieDetailsFake }

        //When
        val result = repositoryImpl.getDetailMovie(640146).subscribeOn(Schedulers.io())

        //Then
        assertThat(result).isEqualTo(movieDetails)
    }

}
package mx.jossprogramming.jlmovieswizel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.jossprogramming.databasemovies.entitys.GenreEntity
import mx.jossprogramming.databasemovies.entitys.MovieGenresCrossRef
import mx.jossprogramming.databasemovies.repository.MoviesRespository
import mx.jossprogramming.jlmovieswizel.domain.GetGenresListUseCase
import mx.jossprogramming.jlmovieswizel.domain.GetLatestMovieUseCase
import mx.jossprogramming.jlmovieswizel.domain.GetMoviesNowPlayingUseCase
import mx.jossprogramming.jlmovieswizel.domain.GetMoviesTopRatedUseCase
import mx.jossprogramming.remote.models.DetailMovies
import mx.jossprogramming.remote.models.getGenres
import mx.jossprogramming.remote.models.toEntity
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCase: GetMoviesNowPlayingUseCase,
    private val moviesRatedUseCase: GetMoviesTopRatedUseCase,
    private val movieLatestUseCase: GetLatestMovieUseCase,
    private val genresListUseCase: GetGenresListUseCase,
    private val moviesRepository: MoviesRespository
):ViewModel() {
    private val _listMoviesNowPlaying = MutableStateFlow(ArrayList<DetailMovies>())
    val listMoviesNowPlaying:StateFlow<ArrayList<DetailMovies>> get() = _listMoviesNowPlaying

    private val _listMoviesTopRated = MutableStateFlow(ArrayList<DetailMovies>())
    val listMoviesTopRated:StateFlow<ArrayList<DetailMovies>> get() = _listMoviesTopRated

    private val _movieLatest = MutableStateFlow(DetailMovies())
    val movieLastest: StateFlow<DetailMovies> get() = _movieLatest

    lateinit var genresObservable: Observable<String>

    init {
        viewModelScope.launch {
            _listMoviesNowPlaying.value = moviesUseCase().results
            _listMoviesTopRated.value = moviesRatedUseCase().results
            _movieLatest.value = movieLatestUseCase()

            //Insert genres catalog
            genresListUseCase().genres.map{
                GenreEntity(idGenre = it.id, name = it.name)
            }.forEach {
                moviesRepository.insertGenre(it)
            }
        }
    }

    fun insertMovieAndGenre(movie:DetailMovies){
        viewModelScope.launch {
            //INSERT MOVIE
            moviesRepository.insertMovie(
                arrayListOf(
                    movie.toEntity()
                )
            )

            //INSERT MOVIES GENRES
            val listGenresMovie = movie.getGenres().map { idGenre ->
                MovieGenresCrossRef(movie.id,idGenre)
            }
            moviesRepository.insertGenresMovie(
                listGenresMovie
            )
        }
    }

    suspend fun getGenresByMovie(idMovie:Int): Observable<List<String>> {
        val scope = coroutineScope {
            async {
                moviesRepository.getMovieWithGenres(idMovie = idMovie)
            }
        }

        val result =  scope.await()
        val genresList = result.genres.map { genre-> genre.name }
        return Observable.create{
            it.onNext(genresList)
            it.onComplete()
        }
    }
}
package mx.jramon.subias.dbmovieproyect.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.jramon.subias.dbmovieproyect.GlobalViewModel
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieDetails
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieEntity
import mx.jramon.subias.dbmovieproyect.movies.domain.model.TvSerieEntity
import mx.jramon.subias.dbmovieproyect.movies.domain.useCase.GetDetailsMovieUseCase
import mx.jramon.subias.dbmovieproyect.movies.domain.useCase.GetMoviePopularUseCase
import mx.jramon.subias.dbmovieproyect.movies.domain.useCase.GetPopularTvSeriesUseCase
import mx.jramon.subias.dbmovieproyect.movies.domain.useCase.GetRatedMoviesUseCase
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
    @Inject constructor(
        private val getPopularMovieUseCase: GetMoviePopularUseCase,
        private val getPopularTvSeriesUseCase: GetPopularTvSeriesUseCase,
        private val getRatedMoviesUseCase: GetRatedMoviesUseCase,
        private val getDetailsMovieUseCase: GetDetailsMovieUseCase
    ): GlobalViewModel() {

   // private val movieRepository: MovieRepository by lazy { MovieRepository() }
    private val diposable = CompositeDisposable()

    //STATE FLOWS
    private val _listMovie = MutableStateFlow<List<MovieEntity>>(emptyList())
    val listMovie: StateFlow<List<MovieEntity>> = _listMovie

    private val _listTvSerie = MutableStateFlow<List<TvSerieEntity>>(emptyList())
    val listTvSerie: StateFlow<List<TvSerieEntity>> = _listTvSerie

    private val _listRatedMovie = MutableStateFlow<List<MovieEntity>>(emptyList())
    val listRatedMovie:StateFlow<List<MovieEntity>> = _listRatedMovie

    private val _detailsMovie = MutableStateFlow<MovieDetails?>(null)
    val detailsMovie:StateFlow<MovieDetails?> = _detailsMovie

    //LIVE DATA
    private val _listMovies = MutableLiveData<List<MovieEntity>>()
    val listMovies:LiveData<List<MovieEntity>> get() = _listMovies

    private val _listTvSeries = MutableLiveData<List<TvSerieEntity>>()
    val listTvSeries:LiveData<List<TvSerieEntity>> get() = _listTvSeries

    private val _listMovieTopRated = MutableLiveData<List<MovieEntity>>()
    val listMovieTopRated: LiveData<List<MovieEntity>> get() = _listMovieTopRated


    fun getListPopularMovies(){
        viewModelScope.launch {
            val popularMovie = getPopularMovieUseCase.invoke(1)
            popularMovie
                .observeOn(Schedulers.io())
                .subscribe({
                  _listMovie.value = it
                },{
                    val errorMsg = when (it) {
                        is HttpException -> "Error de conexión"

                        is IOException -> "Error en el servicio verifique conexión a internet"

                        else -> "Unknown error"
                    }
                    //_errorApi.postValue(errorMsg)

                }).let {
                    diposable.add(it)
                }
        }
    }


    fun getListPopularTvSerie() {
        viewModelScope.launch() {
            val tvSeriesPopular = getPopularTvSeriesUseCase.invoke(1)
            tvSeriesPopular
                .observeOn(Schedulers.io())
                .subscribe({
                    _listTvSerie.value = it
                },{
                    val errorMsg = when (it) {
                        is HttpException -> "Error de conexión"

                        is IOException -> "Error en el servicio verifique conexión a internet"

                        else -> "Unknown error"
                    }
                    //_errorApi.postValue(errorMsg)
                }).let {
                    diposable.add(it)
                }
        }
    }

    fun getListMovieTopRated(){
        viewModelScope.launch {
            val topRatedMovies = getRatedMoviesUseCase.invoke(1)
            topRatedMovies
                .observeOn(Schedulers.io())
                .subscribe({
                    _listRatedMovie.value = it
                },{
                    val errorMsg = when (it) {
                        is HttpException -> "Error de conexión"

                        is IOException -> "Error en el servicio verifique conexión a internet"

                        else -> "Unknown error"
                    }
                    //_errorApi.postValue(errorMsg)

                }).let {
                    diposable.add(it)
                }
        }
    }

    fun getDetailMovie(idMovie:Int){
        viewModelScope.launch {
            val detailMovie = getDetailsMovieUseCase.invoke(idMovie)
            detailMovie
                .observeOn(Schedulers.io())
                .subscribe({
                    _detailsMovie.value = it
                },{
                    val errorMsg = when (it) {
                        is HttpException -> "Error de conexión"

                        is IOException -> "Error en el servicio verifique conexión a internet"

                        else -> "Unknown error"
                    }
                }).let {
                    diposable.add(it)
                }
        }
    }

    override fun onCleared() {
        diposable.clear()
        super.onCleared()
    }
}
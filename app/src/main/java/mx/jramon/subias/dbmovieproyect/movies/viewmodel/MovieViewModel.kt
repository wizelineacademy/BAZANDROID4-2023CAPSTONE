package mx.jramon.subias.dbmovieproyect.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import mx.jramon.subias.dbmovieproyect.GlobalViewModel
import mx.jramon.subias.dbmovieproyect.movies.domain.MovieRepository
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieEntity
import mx.jramon.subias.dbmovieproyect.movies.domain.model.TvSerieEntity
import retrofit2.HttpException
import java.io.IOException

class MovieViewModel : GlobalViewModel() {

    private val movieRepository: MovieRepository by lazy { MovieRepository() }

    private val diposable = CompositeDisposable()

    private val _listMovies = MutableLiveData<List<MovieEntity>>()
    val listMovies:LiveData<List<MovieEntity>> get() = _listMovies

    private val _listTvSeries = MutableLiveData<List<TvSerieEntity>>()
    val listTvSeries:LiveData<List<TvSerieEntity>> get() = _listTvSeries

    private val _listMovieTopRated = MutableLiveData<List<MovieEntity>>()
    val listMovieTopRated: LiveData<List<MovieEntity>> get() = _listMovieTopRated

    fun getListPopularMovies(){
        viewModelScope.launch {
            val popularMovie = movieRepository.getListPopularMovie()
            popularMovie
                .observeOn(Schedulers.io())
                .subscribe({
                  _listMovies.postValue(it.results)
                },{
                    val errorMsg = when (it) {
                        is HttpException -> "Error de conexión"

                        is IOException -> "Error en el servicio verifique conexión a internet"

                        else -> "Unknown error"
                    }
                    _errorApi.postValue(errorMsg)

                }).let {
                    diposable.add(it)
                }
        }
    }


    fun getListPopularTvSerie() {
        viewModelScope.launch() {
            val tvSeriesPopular = movieRepository.getListPopularTvSerie()
            tvSeriesPopular
                .observeOn(Schedulers.io())
                .subscribe({
                    _listTvSeries.postValue(it.listSeries)
                },{
                    val errorMsg = when (it) {
                        is HttpException -> "Error de conexión"

                        is IOException -> "Error en el servicio verifique conexión a internet"

                        else -> "Unknown error"
                    }
                    _errorApi.postValue(errorMsg)
                }).let {
                    diposable.add(it)
                }
        }
    }

    fun getListMovieTopRated(){
        viewModelScope.launch {
            val topRatedMovies = movieRepository.getMovieTopRated()
            topRatedMovies
                .observeOn(Schedulers.io())
                .subscribe({
                    _listMovies.postValue(it.results)
                },{
                    val errorMsg = when (it) {
                        is HttpException -> "Error de conexión"

                        is IOException -> "Error en el servicio verifique conexión a internet"

                        else -> "Unknown error"
                    }
                    _errorApi.postValue(errorMsg)

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
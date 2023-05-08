package com.jecruzv.capstonewl.ui.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.jecruzv.capstonewl.usecases.*
import com.jecruzv.capstonewl.util.Annotations
import com.jecruzv.capstonewl.util.DataState
import com.jecruzv.capstonewl.util.ExploreType
import com.jecruzv.local.dao.MoviesDao
import com.jecruzv.local.model.PopularMovie
import com.jecruzv.local.model.TopRatedMovie
import com.jecruzv.local.model.UpcomingMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@Annotations("Entregable 1")
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase,
    private val getGenresUseCase: GetGenresUseCase,
    private val moviesDao: MoviesDao
) : ViewModel() {

    private val _popularMoviesState = MutableStateFlow(State<PopularMovie>())
    val popularMoviesState: StateFlow<State<PopularMovie>> = _popularMoviesState

    private val disposable = CompositeDisposable()
    private val _upcoming = MutableLiveData<List<UpcomingMovie>>()
    val upcoming: LiveData<List<UpcomingMovie>> = _upcoming

    private val _topRated = MutableStateFlow(State<TopRatedMovie>())
    val topRated: StateFlow<State<TopRatedMovie>> = _topRated

    fun getPopularMovies() {
        getGenresUseCase().onEach { result->

        }.launchIn(viewModelScope)

        getPopularMoviesUseCase(page = 1, ExploreType.Popular).onEach { result ->
            when (result) {
                is DataState.Loading -> {
                    _popularMoviesState.value = State(isLoading = true)
                }
                is DataState.Success -> {
                    _popularMoviesState.value = State(media = result.data ?: emptyList())
                }
                is DataState.Error -> {
                    _popularMoviesState.value = State(media = moviesDao.getPopularMovies().first())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getUpcomingMovies() {
        disposable.add(
            getUpcomingMoviesUseCase(1, ExploreType.Upcoming)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { movies ->
                        // Actualizar el LiveData con la lista de películas
                        _upcoming.value = movies
                    },
                    { error ->
                        runBlocking {
                            _upcoming.value = moviesDao.getUpcomingMovies().first()
                        }
                        Log.e("MyViewModel", "Error obteniendo las próximas películas: $error")
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun getTopRatedMovies() {
        getTopRatedMoviesUseCase(page = 1, ExploreType.TopRated).onEach { result ->
            when (result) {
                is DataState.Loading -> {
                    _topRated.value = State(isLoading = true)
                }
                is DataState.Success -> {
                    _topRated.value = State(media = result.data ?: emptyList())
                }
                is DataState.Error -> {
                    _topRated.value = State(media = moviesDao.getTopRatedMovies().first())
                }
            }
        }.launchIn(viewModelScope)

        /*getMoviesByGenreUseCase("Aventura").onEach {result->
            when (result) {
                is DataState.Loading -> {
                }
                is DataState.Success -> {
                   var movies = State(media = result.data ?: emptyList())
                }
                is DataState.Error -> {
                }
            }
        }.launchIn(viewModelScope)

         */
    }
}
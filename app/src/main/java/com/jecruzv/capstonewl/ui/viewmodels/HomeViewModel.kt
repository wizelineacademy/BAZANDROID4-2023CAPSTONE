package com.jecruzv.capstonewl.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jecruzv.capstonewl.data.Repository
import com.jecruzv.capstonewl.data.model.Movie
import com.jecruzv.capstonewl.data.model.MoviesResponse
import com.jecruzv.capstonewl.util.Annotations
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch


@Annotations("Entregable 1")
class HomeViewModel: ViewModel() {
    private val repository=Repository()

    private val _popular = MutableLiveData<MoviesResponse>()
    val popular: LiveData<MoviesResponse> get() = _popular

    private val _upcoming = MutableLiveData<List<Movie>>()
    val upcoming: LiveData<List<Movie>> = _upcoming

    private val _topRated = MutableLiveData<List<Movie>>()
    val topRated: LiveData<List<Movie>> = _topRated

    //private val repositoryDB = DataBaseRepository(
        //MoviesDatabase .getInstance(application).movieDao())

    //val popularMovies: LiveData<List<Movie>> = repository.popularMovies

    /*init {
        viewModelScope.launch {
            repository.refreshPopularMovies()
        }
    }*/

    /*
    private val _popularMovies = MutableLiveData<List<PopularMoviesDB>>()
    val popularMovies: LiveData<List<PopularMoviesDB>>
        get() = _popularMovies

    init {
        viewModelScope.launch {
            _popularMovies.value = dataBaseRepository.popularMovies.value
        }
    }*/


    fun getPopularMovies() {
        viewModelScope.launch {
            _popular.value=repository.getPopularMovies().await()
        }
    }

    fun getUpcomingMovies() {
        repository.getUpcomingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movies ->
                _upcoming.value = movies
            }, { error ->
            })
    }
    @Annotations("Entregable 2")
    fun getTopRatedMoviesFlow() {
        viewModelScope.launch {
            repository.getTopRatedMoviesFlow()
                .collect { topRated ->
                    _topRated.value = topRated.await().results!!
                }

        }
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            _topRated.value=repository.getTopRatedMovies().await().results!!
        }
    }
}
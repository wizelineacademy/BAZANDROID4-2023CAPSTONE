package mx.jossprogramming.jlmovieswizel.ui.movies

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import mx.jossprogramming.jlmovieswizel.common.Constantes
import mx.jossprogramming.jlmovieswizel.data.models.DetailMovies
import mx.jossprogramming.jlmovieswizel.databinding.ActivityMovieDetailBinding
import mx.jossprogramming.jlmovieswizel.viewmodel.MoviesViewModel

//TODO VISTA DE DETALLE DE PELÍCULA
@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val moviesViewModel: MoviesViewModel by viewModels()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movie =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getSerializableExtra(Constantes.PARAM_ID_MOVIE, DetailMovies::class.java)
            } else {
                intent.getSerializableExtra(Constantes.PARAM_ID_MOVIE) as? DetailMovies
            }

        movie?.let {
            CoroutineScope(Dispatchers.IO).launch {
                val observable = moviesViewModel.getGenresByMovie(movie.id)
                createObservable(observable = observable)
            }
        }

        initUI(movie = movie!!)
    }

    //TODO APLICACION RXJAVA
    private fun createObservable(observable:Observable<List<String>>){
        val observer = object: Observer<List<String>> {
            override fun onSubscribe(d: Disposable) {}

            override fun onError(e: Throwable) {
                Log.e("mensaje","error observer ${e.message}")
            }

            override fun onComplete() {}

            override fun onNext(t: List<String>) {
                binding.txtGenres.text = if(t.isEmpty()){
                    "Not Genres Available"
                }else{
                    t.joinToString(", ")
                }
            }
        }

        observable
            .subscribeOn(Schedulers.io())
            .subscribe(observer)
    }

    private fun initUI(movie: DetailMovies) {
        if(!movie.backdrop_path.isNullOrEmpty()){
            Glide.with(this).load("${Constantes.URL_POSTER}${movie.backdrop_path}").into(binding.imgMainMovie)
        }
        if(!movie.poster_path.isNullOrEmpty()){
            Glide.with(this).load("${Constantes.URL_POSTER}${movie.poster_path}").into(binding.imgPoster)
        }
        binding.txtTitulo.text      = movie.title
        binding.txtSinopsis.text    = movie.overview?:"No available"
        binding.txtRating.text      = movie.vote_average.toString()
    }
}
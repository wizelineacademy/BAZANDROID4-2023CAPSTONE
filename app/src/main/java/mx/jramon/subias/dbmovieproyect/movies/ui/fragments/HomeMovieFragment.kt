package mx.jramon.subias.dbmovieproyect.movies.ui.fragments

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.jramon.subias.dbmovieproyect.base.BaseFragment
import mx.jramon.subias.dbmovieproyect.movies.viewmodel.MovieViewModel
import mx.jramon.subias.dbmovieproyect.R
import mx.jramon.subias.dbmovieproyect.databinding.FragmentHomeMovieBinding
import mx.jramon.subias.dbmovieproyect.movies.ui.adapters.MovieListAdapter
import mx.jramon.subias.dbmovieproyect.movies.ui.adapters.TvSerieListAdapter


@AndroidEntryPoint
class HomeMovieFragment : BaseFragment<FragmentHomeMovieBinding>() {

    private val vModel: MovieViewModel by viewModels()

    override fun getLayout() = R.layout.fragment_home_movie

    override fun initView() {

        //vModel.showLottie(true)

        vModel.getListPopularMovies()

        vBind.tvTittleMovie.setOnClickListener {
            vModel.getListPopularTvSerie()
            vModel.getListMovieTopRated()
        }

        vModel.listMovies.observe(this){
            if(vModel.listMovieTopRated.value != null && vModel.listTvSeries.value != null)
                vModel.showLottie(false)

            vBind.rvMovies.adapter = MovieListAdapter(it){

            }
        }

        vModel.listTvSeries.observe(this){
            if(vModel.listMovieTopRated.value != null && vModel.listMovies.value != null)
                vModel.showLottie(false)

            vBind.rvSeries.adapter = TvSerieListAdapter(it){
            }
        }

        vModel.listMovieTopRated.observe(this){
            if(vModel.listMovies.value != null && vModel.listTvSeries.value != null)
                vModel.showLottie(false)

            vBind.rvMovieTopRated.adapter = MovieListAdapter(it){

            }
        }
    }
}
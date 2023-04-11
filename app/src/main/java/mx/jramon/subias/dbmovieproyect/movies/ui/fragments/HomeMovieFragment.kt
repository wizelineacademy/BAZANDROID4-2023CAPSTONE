package mx.jramon.subias.dbmovieproyect.movies.ui.fragments

import androidx.fragment.app.activityViewModels
import mx.jramon.subias.dbmovieproyect.base.BaseFragment
import mx.jramon.subias.dbmovieproyect.movies.viewmodel.MovieViewModel
import mx.jramon.subias.dbmovieproyect.R
import mx.jramon.subias.dbmovieproyect.databinding.FragmentHomeMovieBinding
import mx.jramon.subias.dbmovieproyect.movies.ui.adapters.MovieListAdapter
import mx.jramon.subias.dbmovieproyect.movies.ui.adapters.TvSerieListAdapter

class HomeMovieFragment : BaseFragment<FragmentHomeMovieBinding>() {

    private val vModel: MovieViewModel by activityViewModels()

    override fun getLayout() = R.layout.fragment_home_movie

    override fun initView() {

        vModel.showLottie(true)

        vModel.getListPopularMovies()
        vModel.getListPopularTvSerie()
        vModel.getListMovieTopRated()

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
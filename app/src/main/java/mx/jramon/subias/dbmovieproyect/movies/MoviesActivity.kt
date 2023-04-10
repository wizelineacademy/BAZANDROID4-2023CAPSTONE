package mx.jramon.subias.dbmovieproyect.movies

import androidx.activity.viewModels
import mx.jramon.subias.dbmovieproyect.R
import mx.jramon.subias.dbmovieproyect.base.BaseActivity
import mx.jramon.subias.dbmovieproyect.databinding.ActivityMoviesRootBinding
import mx.jramon.subias.dbmovieproyect.movies.viewmodel.MovieViewModel

class MoviesActivity : BaseActivity<ActivityMoviesRootBinding>() {

    private val vModel: MovieViewModel by  viewModels()

    override fun getRootLayout() = R.layout.activity_movies_root

    override fun initObserver() {

        vModel.showLottie.observe(this){ show->
            if(show)
                showHideView(DisplayView.SHOW_LOADING_VIEW)
            else
                showHideView(DisplayView.HIDE_LOADING_VIEW)
        }

    }

    override fun initView() {



    }

}
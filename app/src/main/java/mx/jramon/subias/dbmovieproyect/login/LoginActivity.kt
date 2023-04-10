package mx.jramon.subias.dbmovieproyect.login

import android.content.Intent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.jramon.subias.dbmovieproyect.login.viewmodel.LoginViewModel
import mx.jramon.subias.dbmovieproyect.movies.MoviesActivity
import mx.jramon.subias.dbmovieproyect.network.FirebaseCommunication
import mx.jramon.subias.dbmovieproyect.R
import mx.jramon.subias.dbmovieproyect.base.BaseActivity
import mx.jramon.subias.dbmovieproyect.databinding.ActivtyLoginRootBinding

@AndroidEntryPoint
class LoginActivity: BaseActivity<ActivtyLoginRootBinding>() {

    private val vModel: LoginViewModel by viewModels()
    private val firebase: FirebaseCommunication by lazy { FirebaseCommunication() }

    override fun getRootLayout() = R.layout.activty_login_root

    override fun initObserver() {
        vModel.validUser.observe(this){ userCreate->
            vModel.showLottie(false)
            if(userCreate)
                goToMovieActivity()
        }

        vModel.showLottie.observe(this){ show->
            if(show)
                showHideView(DisplayView.SHOW_LOADING_VIEW)
            else
                showHideView(DisplayView.HIDE_LOADING_VIEW)
        }
    }

    override fun initView() {

    }

    override fun onStart() {
        super.onStart()
        if(firebase.auth.currentUser != null){
            goToMovieActivity()
        }
    }

    private fun goToMovieActivity(){
        startActivity(Intent(this, MoviesActivity::class.java).apply {
            this.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }


}
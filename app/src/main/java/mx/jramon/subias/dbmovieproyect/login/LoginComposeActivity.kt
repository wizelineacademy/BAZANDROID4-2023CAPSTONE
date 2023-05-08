package mx.jramon.subias.dbmovieproyect.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.jramon.subias.dbmovieproyect.login.ui.SignInScreen
import mx.jramon.subias.dbmovieproyect.login.ui.SignUpScreen
import mx.jramon.subias.dbmovieproyect.login.ui.routes.LoginRoutes
import mx.jramon.subias.dbmovieproyect.login.viewmodel.LoginViewModel
import mx.jramon.subias.dbmovieproyect.movies.MovieActivity
import mx.jramon.subias.dbmovieproyect.network.FirebaseCommunication
import mx.jramon.subias.dbmovieproyect.ui.components.LoadingLottie


@AndroidEntryPoint
class LoginComposeActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    lateinit var navController: NavHostController
    private val firebase: FirebaseCommunication by lazy { FirebaseCommunication() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            LoadingLottie(show = loginViewModel.lottieState.value)

            navController = rememberNavController()

            NavHost(navController = navController, startDestination = LoginRoutes.SignIn.routes) {
                composable(LoginRoutes.SignIn.routes) {
                    SignInScreen(loginViewModel, navController)
                }
                composable(LoginRoutes.SignUp.routes) {
                    SignUpScreen(loginViewModel, navController)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(firebase.auth.currentUser != null){
            goToMovieActivity()
        }
    }

    override fun onResume() {
        super.onResume()
        loginViewModel.validUser.observe(this) {
            if (it)
                goToMovieActivity()
        }
    }

    private fun goToMovieActivity(){
        loginViewModel._lottieState.value = false
        startActivity(Intent(this, MovieActivity::class.java).apply {
            this.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }
}
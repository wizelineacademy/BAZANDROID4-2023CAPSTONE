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
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieEntity


@AndroidEntryPoint
class LoginComposeActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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

    override fun onResume() {
        super.onResume()
        loginViewModel.validUser.observe(this) {
            if (it)
                startActivity(Intent(this, MovieActivity::class.java))
        }
    }
}
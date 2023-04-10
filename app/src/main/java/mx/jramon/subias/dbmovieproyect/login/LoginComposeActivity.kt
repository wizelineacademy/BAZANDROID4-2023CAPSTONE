package mx.jramon.subias.dbmovieproyect.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.jramon.subias.dbmovieproyect.login.ui.SignInScreen
import mx.jramon.subias.dbmovieproyect.login.ui.SignUpScreen
import mx.jramon.subias.dbmovieproyect.login.viewmodel.LoginViewModel
import mx.jramon.subias.dbmovieproyect.ui.BackgroundDark
import mx.jramon.subias.dbmovieproyect.ui.values.dbMovieTheme


@AndroidEntryPoint
class LoginComposeActivity : ComponentActivity() {

    private val loginViewModel:LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = ""){
                composable("signIn"){
                    SignInScreen(loginViewModel)
                }
                composable("signUp"){
                    SignUpScreen()
                }
            }

        }

    }

}
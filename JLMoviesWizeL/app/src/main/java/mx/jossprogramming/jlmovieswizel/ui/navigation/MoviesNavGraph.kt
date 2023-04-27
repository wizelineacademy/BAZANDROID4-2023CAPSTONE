package mx.jossprogramming.jlmovieswizel.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.jossprogramming.jlmovieswizel.ui.login.LoginScreen
import mx.jossprogramming.jlmovieswizel.ui.movies.MoviesListScreen
import mx.jossprogramming.jlmovieswizel.viewmodel.LoginViewModel
import mx.jossprogramming.jlmovieswizel.viewmodel.MoviesViewModel

@Composable
fun NavigationGraph(loginViewModel: LoginViewModel, moviesViewModel: MoviesViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MoviesRoutes.LoginScreen.route) {
        composable(route = MoviesRoutes.LoginScreen.route) {
            LoginScreen(loginViewModel = loginViewModel) {
                navController.navigate(MoviesRoutes.MoviesListScreen.route)
            }
        }
        composable(route = MoviesRoutes.MoviesListScreen.route) {
            MoviesListScreen(viewModel = moviesViewModel)
        }
    }
}

package mx.jramon.subias.dbmovieproyect.movies

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mx.jramon.subias.dbmovieproyect.movies.ui.screen.DetailsMovieScreen
import mx.jramon.subias.dbmovieproyect.movies.ui.screen.HomeMovieScreen
import mx.jramon.subias.dbmovieproyect.movies.viewmodel.MovieViewModel

@AndroidEntryPoint
class MovieActivity :AppCompatActivity() {

    private val vModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vModel.getListPopularMovies()
        vModel.getListMovieTopRated()
        vModel.getListPopularTvSerie()

        setContent {

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "HomeMovie"){
                composable("HomeMovie"){
                    HomeMovieScreen(vModel, navController)
                }
                composable("DetailsMovie"){
                    DetailsMovieScreen(vModel)
                }
            }

        }
    }
}
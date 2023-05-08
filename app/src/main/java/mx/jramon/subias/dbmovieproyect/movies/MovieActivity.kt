package mx.jramon.subias.dbmovieproyect.movies

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import mx.jramon.subias.dbmovieproyect.movies.ui.screen.DetailsMovieScreen
import mx.jramon.subias.dbmovieproyect.movies.ui.screen.HomeMovieScreen
import mx.jramon.subias.dbmovieproyect.movies.viewmodel.MovieViewModel
import mx.jramon.subias.dbmovieproyect.ui.components.LoadingLottie

@AndroidEntryPoint
class MovieActivity : ComponentActivity() {

    private val vModel: MovieViewModel by viewModels()
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch{
            vModel.detailsMovie.collect{
                if(it != null)
                    navController.navigate("DetailsMovie")
            }
        }

        vModel.getListPopularMovies()
        vModel.getListMovieTopRated()
        vModel.getListPopularTvSerie()

        setContent {

            if(vModel.error.value){
                Toast.makeText(this, "Hubo un error al cargar la pelicula", Toast.LENGTH_SHORT).show()
                vModel._error.value = false
            }

            navController = rememberNavController()

            NavHost(navController = navController, startDestination = "HomeMovie"){
                composable("HomeMovie"){
                    HomeMovieScreen(vModel, navController)
                }
                composable("DetailsMovie"){
                    DetailsMovieScreen(vModel)
                }
            }
            LoadingLottie(show = vModel.lottieState.value)
        }
    }
}
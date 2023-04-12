package mx.jossprogramming.jlmovieswizel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import mx.jossprogramming.jlmovieswizel.ui.navigation.NavigationGraph
import mx.jossprogramming.jlmovieswizel.ui.theme.JLMoviesWizeLTheme
import mx.jossprogramming.jlmovieswizel.viewmodel.LoginViewModel
import mx.jossprogramming.jlmovieswizel.viewmodel.MoviesViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private val moviesViewModel: MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JLMoviesWizeLTheme {
                NavigationGraph(loginViewModel = viewModel, moviesViewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JLMoviesWizeLTheme {
        Greeting("Android")
    }
}
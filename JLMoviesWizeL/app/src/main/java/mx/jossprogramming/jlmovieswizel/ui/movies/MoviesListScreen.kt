package mx.jossprogramming.jlmovieswizel.ui.movies

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import mx.jossprogramming.jlmovieswizel.R
import mx.jossprogramming.jlmovieswizel.common.Constantes
import mx.jossprogramming.jlmovieswizel.common.Constantes.PARAM_ID_MOVIE
import mx.jossprogramming.jlmovieswizel.viewmodel.MoviesViewModel
import mx.jossprogramming.remote.models.DetailMovies

@Composable
fun MoviesListScreen(viewModel: MoviesViewModel) {
    val moviesNowPlayingList: ArrayList<DetailMovies> by viewModel.listMoviesNowPlaying.collectAsState()
    val moviesTopratedList:ArrayList<DetailMovies> by viewModel.listMoviesTopRated.collectAsState()
    val moviesLatest:DetailMovies by viewModel.movieLastest.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        if (moviesNowPlayingList.isEmpty()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        } else {
            Body(
                moviesNowPlayingList = moviesNowPlayingList,
                moviesTopRatedList = moviesTopratedList,
                movieLatest = moviesLatest,
                moviesViewModel = viewModel
            )
        }
    }
}

@Composable
fun Body(moviesNowPlayingList: ArrayList<DetailMovies>,
         moviesTopRatedList: ArrayList<DetailMovies>,
         movieLatest:DetailMovies,
         moviesViewModel: MoviesViewModel
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Text(
            "Latest",
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 8.dp),
            color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        GenerateCardLatestMovie(
            movie = movieLatest
        ) { moviesViewModel.insertMovieAndGenre(movieLatest) }
        Divider(color = Color.Gray)
        Text(
            "Top Rated",
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 8.dp),
            color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        GenerateHorizontalList(moviesList = moviesTopRatedList, viewModel = moviesViewModel)
        Text(
            "Movies Now Playing",
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 8.dp),
            color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        GenerateHorizontalList(moviesList = moviesNowPlayingList, viewModel = moviesViewModel)
        Divider(color = Color.Gray)
    }
}

@Composable
fun GenerateCardLatestMovie(movie: DetailMovies, onClickCard: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    )
    {
        MovieCard(movie = movie, onClickItemAction = onClickCard)
        Text(
            text = movie.title,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(Color.Yellow)
                .padding(horizontal = 4.dp),
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
fun GenerateHorizontalList(moviesList: ArrayList<DetailMovies>,viewModel: MoviesViewModel) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier.height(500.dp)
    ) {
        items(moviesList) {
            MovieCard(movie = it){
                viewModel.insertMovieAndGenre(it)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieCard(movie: DetailMovies,onClickItemAction:()->Unit) {
    val urlPhoto = Constantes.URL_POSTER + movie.poster_path
    val context = LocalContext.current
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp),
        onClick = {
            onClickItemAction.invoke()

            val intent = Intent(context,MovieDetailActivity::class.java)
            /*val bundle = Bundle()
            bundle.putInt(PARAM_ID_MOVIE,movie.id)*/
            intent.putExtra(PARAM_ID_MOVIE,movie)
            context.startActivity(intent)
        }
    ) {
        Box() {
            if(movie.poster_path.isNullOrEmpty()){
                Image(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    painter = painterResource(id = R.drawable.baseline_hide_image_24), contentDescription = "not image")
            }
            else{
                SubcomposeAsyncImage(
                    model = urlPhoto,
                    loading = {
                        Box(modifier = Modifier.width(100.dp)){
                            CircularProgressIndicator(modifier = Modifier.width(100.dp))
                        }
                    },
                    contentDescription = movie.title
                )
            }
        }
    }
}
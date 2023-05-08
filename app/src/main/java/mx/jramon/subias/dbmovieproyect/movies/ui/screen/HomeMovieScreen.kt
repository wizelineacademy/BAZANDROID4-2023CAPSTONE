package mx.jramon.subias.dbmovieproyect.movies.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import mx.jramon.subias.dbmovieproyect.movies.viewmodel.MovieViewModel
import mx.jramon.subias.dbmovieproyect.ui.components.ItemMovieComponent

@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeMovieScreen(
    vModel: MovieViewModel,
    navController: NavHostController
) {

    val listPopularMovie = vModel.listMovie.collectAsState()
    val listPopularTvSerie = vModel.listTvSerie.collectAsState()
    val listRatedMovie = vModel.listRatedMovie.collectAsState()

    Column() {
        LazyRow(
            modifier = Modifier.padding(top = 10.dp)
        ){
            items(count = listPopularMovie.value.size, itemContent = { item->
                ItemMovieComponent(
                    url = listPopularMovie.value[item].posterPath?:"",
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            vModel.getDetailMovie(listPopularMovie.value[item].id)
                        }
                )
            })
        }

        LazyRow(
            modifier = Modifier.padding(top = 10.dp)
        ){
            items(count = listPopularTvSerie.value.size, itemContent = { item->
                ItemMovieComponent(
                    url = listPopularTvSerie.value[item].posterPath?:"",
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            // onClick(listPopularMovie.value[item].id)
                        }
                )
            })
        }

        LazyRow(
            modifier = Modifier.padding(top = 10.dp)
        ){
            items(count = listRatedMovie.value.size, itemContent = { item->
                ItemMovieComponent(
                    url = listRatedMovie.value[item].posterPath?:"",
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            vModel.getDetailMovie(listPopularMovie.value[item].id)
                        }
                )
            })
        }
    }
}
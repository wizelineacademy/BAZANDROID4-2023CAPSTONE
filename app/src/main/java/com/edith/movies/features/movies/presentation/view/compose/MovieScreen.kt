package com.edith.movies.features.movies.presentation.view.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edith.movies.features.movies.presentation.viewmodel.MoviesViewModel




@Composable
fun MovieScreen(
    viewModel : MoviesViewModel ,
    modifier: Modifier = Modifier
){
      var movieState = viewModel.currentMovie
    Column(modifier = modifier.padding(16.dp)) {
    DetailMovie()
    }
}

@Preview
@Composable
fun DetailMovie(modifier:Modifier = Modifier){
    val image = painterResource(android.R.drawable.ic_menu_myplaces)


    Column(modifier = modifier) {


        Image(painter = image,
            contentDescription =  "Icono android",
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.tint(
                Color.Green
            ))
        
        Text(text = "Pelicula" ,
            modifier = Modifier.wrapContentWidth(Alignment.Start),
            style = TextStyle(fontSize = 18.sp)
        )
    }

}
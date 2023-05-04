package com.example.themoviedb.presentation.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.core.model.MovieModel
import com.example.themoviedb.R
import com.example.themoviedb.presentation.viewmodel.MovieViewModel
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieDetail(
    movie: MovieModel = MovieModel(),
    movieViewModel: MovieViewModel,
    onBackToolBar: () -> Unit
) {

    val genres = movieViewModel.genresMovieState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = movie.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackToolBar.invoke()
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                }
            )
        }
    ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                GlideImage(
                    imageModel = {
                        movie.imageUrl
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    previewPlaceholder = R.drawable.placeholder
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        text = movie.description,
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    genres.value?.let {
                        Text(
                            text = stringResource(id = R.string.text_genres),
                        )
                        LazyColumn(content = {
                            items(it) { genre ->
                                Text(
                                    text = genre.name
                                )
                            }
                        })
                    }
                }
            }
    }
}
package mx.jramon.subias.dbmovieproyect.movies.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import mx.jramon.subias.dbmovieproyect.movies.viewmodel.MovieViewModel
import mx.jramon.subias.dbmovieproyect.ui.White
import mx.jramon.subias.dbmovieproyect.ui.components.RatingBarComponent
import mx.jramon.subias.dbmovieproyect.utils.Constants.IMAGE_API_PREFIX

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailsMovieScreen(vModel: MovieViewModel) {

    val movieInfo by vModel.detailsMovie.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        GlideImage(
            model = "$IMAGE_API_PREFIX${movieInfo?.poster_path}",
            contentDescription = "detailsMovie",
            alpha = 0.1f,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 100.dp)

        ) {

            GlideImage(
                model = "$IMAGE_API_PREFIX${movieInfo?.poster_path}",
                contentDescription = "itemMovie",
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(
                        width = 200.dp,
                        height = 300.dp
                    )
                    .background(color = Color.Transparent, shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .align(Alignment.CenterHorizontally)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = movieInfo?.title ?: "",
                    color = White,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = movieInfo?.overview ?: "",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp),
                    textAlign = TextAlign.Center
                )

                RatingBarComponent(
                    rated = movieInfo?.vote_average ?: 0.0f,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

        }
    }
}

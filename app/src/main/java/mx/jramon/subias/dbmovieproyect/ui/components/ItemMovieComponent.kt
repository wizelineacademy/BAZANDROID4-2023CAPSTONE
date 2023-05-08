package mx.jramon.subias.dbmovieproyect.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import mx.jramon.subias.dbmovieproyect.utils.Constants

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ItemMovieComponent(
    url:String,
    modifier: Modifier = Modifier.padding(10.dp),
) {
    Column(modifier) {
        GlideImage(
            model = "${Constants.IMAGE_API_PREFIX}$url",
            contentDescription = "itemMovie",
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(
                    width = 100.dp,
                    height = 150.dp
                )
                .background(color = Transparent, shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
        )
    }
}
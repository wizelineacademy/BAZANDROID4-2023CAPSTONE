package com.jecruzv.capstonewl.ui.fragment

import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import com.jecruzv.capstonewl.R
import com.jecruzv.capstonewl.util.Constants.BASE_POSTER_IMAGE_URL

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImageCard(
    imageUrl: String?,
    title: String?,
    rating: Double? = 0.0,
    indication: Indication? = rememberRipple(),
    onItemClick: () -> Unit
) {
    Card(
        onClick = onItemClick,
        modifier = Modifier
            .width(100.dp)
            .height(150.dp),
        shape = MaterialTheme.shapes.large,
        elevation = 4.dp
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberImagePainter(
                    data = BASE_POSTER_IMAGE_URL + imageUrl,
                    builder = {
                        crossfade(true)
                        error(R.drawable.ic_placeholder)
                        memoryCachePolicy(CachePolicy.ENABLED)
                        diskCachePolicy(CachePolicy.DISABLED)
                        networkCachePolicy(CachePolicy.ENABLED)
                    }
                ),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
            )
            if (rating?.let { it.compareTo(0f) > 0 } == true) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "$rating",
                            style = MaterialTheme.typography.h6,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .background(
                                    Color.Black.copy(alpha = 0.7f),
                                    RoundedCornerShape(bottomStart = 12.dp)
                                )
                                .padding(start = 4.dp, end = 2.dp)
                                .padding(vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}
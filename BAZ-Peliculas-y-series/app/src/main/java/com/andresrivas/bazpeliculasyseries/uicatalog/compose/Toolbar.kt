package com.andresrivas.bazpeliculasyseries.uicatalog.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andresrivas.bazpeliculasyseries.R
import com.andresrivas.bazpeliculasyseries.uicatalog.compose.model.ToolbarModel
import com.andresrivas.bazpeliculasyseries.uicatalog.theme.BlackTransparent

@Composable
fun Toolbar(
    toolbarModel: ToolbarModel,
    onStartClick: (() -> Unit)? = null,
    onEndClick: (() -> Unit)? = null,
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.densityDpi
    val topHeaderHeight = screenDensity(screenHeight = screenHeight)
    Surface(modifier = Modifier.height(topHeaderHeight.dp)) {
        Row(
            modifier = Modifier
                .background(toolbarModel.backgroundColor)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            toolbarModel.startIcon?.let {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(toolbarModel.backgroundColor)
                        .clickable { onStartClick?.invoke() }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Back arrow",
                        modifier = Modifier
                            .height(32.dp)
                            .width(32.dp)
                    )
                }
            }
            Text(text = toolbarModel.text, fontSize = 25.sp)
            toolbarModel.endIcon?.let {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(toolbarModel.backgroundColor)
                        .clickable { onEndClick?.invoke() }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Saved icon",
                        modifier = Modifier
                            .height(28.dp)
                            .width(28.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ToolbarPreview() {
    Toolbar(
        ToolbarModel(
            "Movie Detail!",
            R.drawable.ic_back_arrow,
            R.drawable.ic_favorites,
            BlackTransparent
        )
    )
}


@Composable
fun screenDensity(screenHeight: Int): Int {
    var topHeaderHeight = 0
    when (screenHeight) {
        in 200..300 -> topHeaderHeight = 62
        in 301..500 -> topHeaderHeight = 72
        in 501..650 -> topHeaderHeight = 72
    }
    return topHeaderHeight
}
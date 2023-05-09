package com.andresrivas.bazpeliculasyseries.uicatalog.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
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
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            toolbarModel.startIcon?.let {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(toolbarModel.backgroundColor)
                        .clickable { onStartClick?.invoke() }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Back arrow",
                        modifier = Modifier
                            .height(32.dp)
                            .width(32.dp),
                        colorFilter = ColorFilter.tint(toolbarModel.elementsColor),
                    )
                }
            }
            Text(text = toolbarModel.text, color = toolbarModel.elementsColor, fontSize = 16.sp)
            toolbarModel.endIcon?.let {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(toolbarModel.backgroundColor)
                        .clickable { onEndClick?.invoke() }
                        .padding(8.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Saved icon",
                        modifier = Modifier
                            .height(28.dp)
                            .width(28.dp),
                        colorFilter = ColorFilter.tint(toolbarModel.elementsColor),
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
            BlackTransparent,
        ),
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

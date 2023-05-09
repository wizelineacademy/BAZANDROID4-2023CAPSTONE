package com.andresrivas.bazpeliculasyseries.uicatalog.compose.model

import androidx.compose.ui.graphics.Color
import com.andresrivas.bazpeliculasyseries.uicatalog.theme.PrimaryColor

data class ToolbarModel(
    val text: String = "",
    val startIcon: Int? = null,
    val endIcon: Int? = null,
    val backgroundColor: Color = PrimaryColor,
    val elementsColor: Color = Color.Black,
)

package com.example.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val title: String = "",
    val imageUrl: String = "",
    val description: String = "",
    val genreIds: List<Int> = emptyList(),
    val genres: List<GenreModel> = emptyList()
): Parcelable
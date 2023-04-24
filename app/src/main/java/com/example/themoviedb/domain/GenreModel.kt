package com.example.themoviedb.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreModel(
    val id: Int = 0,
    val name: String = ""
): Parcelable
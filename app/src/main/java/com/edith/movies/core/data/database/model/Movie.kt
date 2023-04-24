package com.edith.movies.core.data.database.model

import com.edith.movies.core.data.database.entity.MovieEntity

data class Movie(
    val id: Int,
    val backdrop_path: String,
    val original_title: String,
    val poster_path: String,
    val title: String
)

fun MovieModel.toDomain() = Movie(id, backdrop_path, original_title,poster_path, title)

fun MovieEntity.toDomain() = Movie(id, backdrop_path, original_title,poster_path, title)

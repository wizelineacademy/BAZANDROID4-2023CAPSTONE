package com.example.themoviedb.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TopRatedMovies")
data class TopRatedLocal(
    @PrimaryKey
    val id: Int = 0,
    val title: String,
    val posterPath: String
)

package com.example.themoviedb.data.datasource.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LatestMovies")
data class LatestLocal(
    @PrimaryKey
    val id: Int = 0,
    val title: String,
    val posterPath: String,
    val description: String,
    @ColumnInfo(name = "genre_ids")
    var genreIds: List<Int>,
)
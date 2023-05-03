package com.example.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GenresMovies")
data class GenreLocal(
    @PrimaryKey
    val idGenre: Int = 0,
    val name: String = ""
)

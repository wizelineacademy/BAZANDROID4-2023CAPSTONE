package com.edith.movies.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.edith.movies.core.data.database.model.Movie


@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val backdrop_path: String,
    //val genre_ids: List<Int>,
    val original_title: String,
    val poster_path: String,
    val title: String
)

fun Movie.toDatabase() = MovieEntity(id = id,  backdrop_path =  backdrop_path, original_title = original_title,
                                    poster_path = poster_path, title =  title)

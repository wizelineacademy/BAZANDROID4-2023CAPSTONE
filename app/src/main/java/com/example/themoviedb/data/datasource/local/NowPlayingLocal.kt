package com.example.themoviedb.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.themoviedb.domain.MovieModel

@Entity(tableName = "NowPlayingMovies")
data class NowPlayingLocal(
    @PrimaryKey
    val id: Int = 0,
    val title: String,
    val posterPath: String
)

fun List<NowPlayingLocal>.toModel(): List<MovieModel> {
    return map {
        MovieModel(
            title = it.title,
            imageUrl = "https://image.tmdb.org/t/p/w500/".plus(it.posterPath)
        )
    }
}
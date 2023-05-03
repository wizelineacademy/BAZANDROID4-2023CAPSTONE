package com.example.local.entity

import androidx.room.*

@Entity(
    tableName = "NowPlayingMovies"
)
data class NowPlayingLocal(
    @PrimaryKey
    val id: Int = 0,
    val title: String,
    val posterPath: String,
    val description: String,
    @ColumnInfo(name = "genre_ids")
    var genreIds: List<Int>,
)

data class NowPlayingLocalWithGenres(
    @Embedded val nowPlayingLocal: NowPlayingLocal,
    @Relation(
        parentColumn = "genre_ids",
        entityColumn = "idGenre",
        entity = GenreLocal::class,
    )
    val genres: List<GenreLocal>
)

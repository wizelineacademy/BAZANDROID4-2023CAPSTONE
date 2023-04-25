package com.jecruzv.capstonewl.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jecruzv.capstonewl.util.Annotations

@Annotations("Entregable 2")
@Entity(tableName = "popular_movies")
data class PopularMoviesDB(
    @PrimaryKey val id: Int,
    val title: String,
    val overview: String?,
    @ColumnInfo(name = "release_date") val release_date: String,
    @ColumnInfo(name = "poster_path") val poster_path: String?,
    @ColumnInfo(name = "vote_average") val vote_average: Double
)
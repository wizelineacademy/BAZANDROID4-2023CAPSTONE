package com.jecruzv.capstonewl.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jecruzv.capstonewl.util.Constants

@Entity(tableName = Constants.TOP_RATED_MOVIE_TABLE)
data class TopRatedMovie(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int?,
    @ColumnInfo(name = "adult") var adult: Boolean? = false,
    @ColumnInfo(name = "backdrop_path") var backdrop_path: String? = "",
    @ColumnInfo(name = "original_language") var original_language: String? = "",
    @ColumnInfo(name = "original_title") var original_title: String? = "",
    @ColumnInfo(name = "overview") var overview: String? = "",
    @ColumnInfo(name = "popularity") var popularity: Double? = 0.0,
    @ColumnInfo(name = "video") var video: Boolean? = false,
    @ColumnInfo(name = "poster_path") var poster_path: String? = "",
    @ColumnInfo(name = "release_date") var release_date: String? = "",
    @ColumnInfo(name = "title") var title: String? = "",
    @ColumnInfo(name = "vote_average") var vote_average: Double? = 0.0,
    @ColumnInfo(name = "vote_count") var vote_count: Int? = 0,
    @ColumnInfo(name = "genre_ids") val genre_ids: List<Int>?,
)
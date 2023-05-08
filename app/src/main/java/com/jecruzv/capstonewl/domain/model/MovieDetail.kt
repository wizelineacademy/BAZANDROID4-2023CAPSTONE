package com.jecruzv.capstonewl.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.jecruzv.capstonewl.util.Annotations
import com.jecruzv.capstonewl.util.Constants
import java.util.*

@Annotations("Entregable 2")
@Entity(tableName = Constants.MOVIE_TABLE)
data class MovieDetail(
    @ColumnInfo(name = "adult") var adult: Boolean? = null,
    @Ignore @ColumnInfo(name = "backdrop_path") var backdrop_path: String? = null,
    @Ignore var genres: List<Genres>? = emptyList(),
    @PrimaryKey @ColumnInfo(name = "id") var id: Int? = null,
    @ColumnInfo(name = "imdb_id") var imdb_id: String? = null,
    @ColumnInfo(name = "original_title") var original_title: String? = null,
    @ColumnInfo(name = "overview") var overview: String? = null,
    @ColumnInfo(name = "popularity") var popularity: Double? = null,
    @ColumnInfo(name = "poster_path") var poster_path: String? = null,
    @ColumnInfo(name = "release_date") var release_date: String? = null,
    @ColumnInfo(name = "runtime") var runtime: Int? = null,
    @ColumnInfo(name = "status") var status: String? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "vote_average") var vote_average: Double? = null,
    @ColumnInfo(name = "vote_count") var vote_count: Int? = null,
    @ColumnInfo(name = "budget") var budget: Long? = null,
    @ColumnInfo(name = "revenue") var revenue: Long? = null,
    @ColumnInfo(name = "added_date") var added_date: Date? = null,
    @ColumnInfo(name = "my_rating") var my_rating: Float? = null,
    @Ignore var spoken_languages: List<Language>? = emptyList(),
    @Ignore var homepage: String? = null,
    @Ignore var originalLanguage: String? = null,
    @Ignore var tagline: String? = null,
    @Ignore var video: Boolean? = false
)
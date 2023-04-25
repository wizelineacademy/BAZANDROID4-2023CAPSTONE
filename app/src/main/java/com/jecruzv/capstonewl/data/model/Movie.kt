package com.jecruzv.capstonewl.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie (
    val adult: Boolean?,
    val backdrop_path: String?,
    val poster_path: String?,
    val genre_ids: List<Int>?,
    val genres: List<Genre>?,
    val media_type: String?,
    val id: Int?,
    val imdb_id: String?,
    val original_language: String?,
    val overview: String?,
    val popularity: Double?,
    val release_date: String?,
    val runtime: Int?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)
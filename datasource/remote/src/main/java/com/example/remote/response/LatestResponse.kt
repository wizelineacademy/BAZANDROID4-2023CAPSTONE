package com.example.remote.response

data class LatestResponse(
    val adult: Boolean? = null,
    val backdrop_path: Any? = null,
    val belongs_to_collection: Any? = null,
    val budget: Int? = null,
    val homepage: String? = null,
    val id: Int? = null,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Int? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val revenue: Int? = null,
    val runtime: Int? = null,
    val spoken_languages: List<Any?>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Int? = null,
    val vote_count: Int? = null
)
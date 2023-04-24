package com.edith.movies.core.data.database.model

import com.edith.movies.core.data.database.model.GenderModel

data class LastMoviesResponse(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: String,
    val budget: Int,
    val genres: List<GenderModel>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String, //title
    val overview: String, //descripcion
    val popularity: Double,
    val poster_path: String,  ///imagen
    val production_companies: List<Any>,
    val production_countries: List<Any>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: List<Any>,
    val status: String,
    val tagline: String,
    val title: String, //title
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
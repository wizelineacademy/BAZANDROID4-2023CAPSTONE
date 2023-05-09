package com.andresrivas.bazpeliculasyseries.data.services.moviesapi.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("dates") var dates: Dates?,
    @SerializedName("page") var page: Int?,
    @SerializedName("results") var resultList: List<MovieResult?> = emptyList(),
    @SerializedName("total_pages") var totalPages: Int?,
    @SerializedName("total_results") var totalResults: Int?,
)

data class MovieResult(
    @SerializedName("adult") var adult: Boolean?,
    @SerializedName("backdrop_path") var backdropPath: String?,
    @SerializedName("genre_ids") var genreIds: List<Int>?,
    @SerializedName("id") var id: String?,
    @SerializedName("original_language") var originalLanguage: String?,
    @SerializedName("original_title") var originalTitle: String?,
    @SerializedName("overview") var overView: String?,
    @SerializedName("popularity") var popularity: Double?,
    @SerializedName("poster_path") var posterPath: String?,
    @SerializedName("release_date") var releaseDate: String?,
    @SerializedName("title") var title: String?,
    @SerializedName("video") var video: Boolean?,
    @SerializedName("vote_average") var voteAverage: Double?,
    @SerializedName("vote_count") var voteCount: Double?,
)

data class Dates(
    @SerializedName("maximum") var maximum: String?,
    @SerializedName("minimum") var minimum: String?,
)

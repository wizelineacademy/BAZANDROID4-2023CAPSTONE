package com.example.themoviedb.data.model

import com.example.themoviedb.data.datasource.local.LatestLocal
import com.example.themoviedb.data.datasource.local.NowPlayingLocal
import com.example.themoviedb.data.datasource.local.TopRatedLocal
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) {
    data class Result(
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("backdrop_path")
        val backdropPath: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        @SerializedName("id")
        val id: Int,
        @SerializedName("original_language")
        val originalLanguage: String,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("poster_path")
        val posterPath: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("vote_count")
        val voteCount: Int
    )
}

fun List<MovieResponse.Result>.toNowPlayingEntity(): List<NowPlayingLocal> {
    return mapIndexed { i, it ->
        NowPlayingLocal(
            id = i,
            title = it.title,
            posterPath = it.posterPath
        )
    }
}

fun List<MovieResponse.Result>.toLatestEntity(): List<LatestLocal> {
    return mapIndexed { i, it ->
        LatestLocal(
            id = i,
            title = it.title,
            posterPath = it.posterPath
        )
    }
}

fun List<MovieResponse.Result>.toTopRatedEntity(): List<TopRatedLocal> {
    return mapIndexed { i, it ->
        TopRatedLocal(
            id = i,
            title = it.title,
            posterPath = it.posterPath
        )
    }
}
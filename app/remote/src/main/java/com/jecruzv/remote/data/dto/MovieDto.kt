package com.jecruzv.remote.data.dto

import com.jecruzv.local.model.*
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDto (
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

fun MovieDto.toPopularMovie() : PopularMovie {
    return this.let {
        PopularMovie(
            adult = it.adult,
            backdrop_path = it.backdrop_path,
            genre_ids = it.genre_ids!!,
            id = it.id,
            original_language = it.original_language,
            original_title = it.original_title,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = it.poster_path,
            release_date = it.release_date,
            title = it.title,
            video = it.video,
            vote_average = it.vote_average,
            vote_count = it.vote_count
        )
    }
}

fun MovieDto.toTopRatedMovie() : TopRatedMovie {
    return this.let {
        TopRatedMovie(
            adult = it.adult,
            backdrop_path = it.backdrop_path,
            genre_ids = it.genre_ids!!,
            id = it.id,
            original_language = it.original_language,
            original_title = it.original_title,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = it.poster_path,
            release_date = it.release_date,
            title = it.title,
            video = it.video,
            vote_average = it.vote_average,
            vote_count = it.vote_count
        )
    }
}

fun MovieDto.toUpcomingMovie() : UpcomingMovie {
    return this.let {
        UpcomingMovie(
            adult = it.adult,
            backdrop_path = it.backdrop_path,
            genre_ids = it.genre_ids!!,
            id = it.id,
            original_language = it.original_language,
            original_title = it.original_title,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = it.poster_path,
            release_date = it.release_date,
            title = it.title,
            video = it.video,
            vote_average = it.vote_average,
            vote_count = it.vote_count
        )
    }
}
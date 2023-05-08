package com.jecruzv.capstonewl.data.remote.dto

import com.jecruzv.capstonewl.domain.model.Language
import com.jecruzv.capstonewl.domain.model.MovieDetail
import com.jecruzv.capstonewl.domain.model.Genres
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailDto (
    val adult: Boolean?,
    val backdrop_path: String?,
    val budget: Long?,
    val genres: List<GenresDto>?,
    val homepage: String?,
    val id: Int?,
    val imdb_id: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val revenue: Long?,
    val runtime: Int?,
    val spoken_languages: List<LanguageDto>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

fun MovieDetailDto.toMovie() : MovieDetail {
    return this.let {
        MovieDetail(
            adult = it.adult,
            backdrop_path = it.backdrop_path,
            budget = it.budget,
            genres = map(it.genres!!),
            homepage = it.homepage,
            id = it.id,
            imdb_id = it.imdb_id,
            originalLanguage = it.original_language,
            original_title = it.original_title,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = it.poster_path,
            release_date = it.release_date,
            revenue = it.revenue,
            runtime = it.runtime,
            spoken_languages = map(it.spoken_languages!!),
            status = it.status,
            tagline = it.tagline,
            title = it.title,
            video = it.video,
            vote_average = it.vote_average,
            vote_count = it.vote_count
        )
    }
}

@JvmName("mapGenres")
fun map(genres : List<GenresDto>) : List<Genres>? {
    return genres.map {
        Genres(
            id = it.id,
            name = it.name
        )
    }
}

@JvmName("mapSpokenLanguages")
fun map(spokenLanguages: List<LanguageDto>) : List<Language>? {
    return spokenLanguages.map {
        Language(
            english_name = it.english_name,
            iso6391 = it.iso_639_1,
            name = it.name
        )
    }
}


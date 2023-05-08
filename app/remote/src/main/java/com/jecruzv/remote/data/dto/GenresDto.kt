package com.jecruzv.remote.data.dto

import com.jecruzv.local.model.Genres
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenresDto(
    val id: Int?,
    val name: String?
)

fun GenresDto.toGenres() : Genres {
    return this.let {
        Genres(
            id = it.id,
            name=it.name
        )
    }
}

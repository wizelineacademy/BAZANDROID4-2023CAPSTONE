package com.jecruzv.capstonewl.data.remote.dto

import com.jecruzv.capstonewl.domain.model.Genres
import com.jecruzv.capstonewl.domain.model.PopularMovie
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

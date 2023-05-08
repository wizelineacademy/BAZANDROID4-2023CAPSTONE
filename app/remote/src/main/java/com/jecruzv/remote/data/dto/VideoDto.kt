package com.jecruzv.remote.data.dto

import com.jecruzv.local.model.Video
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoDto(
    val id: String,
    val iso_3166_1: String,
    val iso_639_1: String,
    val key: String,
    val name: String,
    val official: Boolean,
    val published_at: String,
    val site: String,
    val size: Int,
    val type: String
)

fun VideoDto.toVideo(): Video {
    return this.let {
        Video(
            id = it.id,
            iso_3166_1 = it.iso_3166_1,
            iso_639_1 = it.iso_639_1,
            key = it.key,
            name = it.name,
            official = it.official,
            published_at = it.published_at,
            site = it.site,
            size = it.size,
            type = it.type
        )
    }
}

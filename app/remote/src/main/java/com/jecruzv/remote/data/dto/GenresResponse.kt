package com.jecruzv.remote.data.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenresResponse(
    val genres: List<GenresDto>
)
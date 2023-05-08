package com.jecruzv.remote.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoResponseDto(
        @SerializedName("id")
        @Expose val id: Int,
        @SerializedName("results")
        @Expose val results: List<VideoDto>
    )
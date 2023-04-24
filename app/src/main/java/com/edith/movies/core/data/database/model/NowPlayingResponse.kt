package com.edith.movies.core.data.database.model

import com.edith.movies.core.data.database.model.DatesModel
import com.edith.movies.core.data.database.model.MovieModel
import com.google.gson.annotations.SerializedName

data class NowPlayingResponse(
    @SerializedName("dates")  val datesModel: DatesModel,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieModel>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int
)
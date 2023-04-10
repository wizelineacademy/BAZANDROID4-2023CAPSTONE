package com.edith.movies.core.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesDbResponse(


    @SerializedName("page")  val page: Int,
    @SerializedName("results") val results: List<MovieDb>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int,
) : Parcelable
package com.jecruzv.capstonewl.data.model

import com.google.gson.annotations.SerializedName

data class Movie (
    var adult: Boolean = false,
    @SerializedName("backdrop_path")
    var backdropPath: String = "",
    var id: String = "",
    var overview: String = "",
    @SerializedName("poster_path")
    var posterPath: String? = "",
    @SerializedName("release_date")
    var releaseDate: String = "",
    var title: String = "",
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    var voteCount: Int = 0
)
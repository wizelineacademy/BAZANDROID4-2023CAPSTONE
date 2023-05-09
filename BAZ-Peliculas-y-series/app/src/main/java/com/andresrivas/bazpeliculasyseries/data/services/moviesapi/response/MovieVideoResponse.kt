package com.andresrivas.bazpeliculasyseries.data.services.moviesapi.response

import com.google.gson.annotations.SerializedName

data class MovieVideoResponse(
    @SerializedName("id") var id: Int?,
    @SerializedName("results") var resultList: List<MovieVideoResult?>,
)

data class MovieVideoResult(
    @SerializedName("id") var id: String?,
    @SerializedName("key") var key: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("site") var site: String?,
    @SerializedName("size") var size: Int?,
    @SerializedName("type") var type: String?,
)

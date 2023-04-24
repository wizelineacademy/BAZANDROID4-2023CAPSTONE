package com.example.themoviedb.data.model


import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres: List<Genre>? = null
) {
    data class Genre(
        @SerializedName("id")
        val id: Int? = null,
        @SerializedName("name")
        val name: String? = null
    )
}
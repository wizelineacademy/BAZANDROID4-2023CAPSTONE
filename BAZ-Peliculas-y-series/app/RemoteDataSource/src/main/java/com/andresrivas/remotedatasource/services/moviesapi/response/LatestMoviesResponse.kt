package com.andresrivas.remotedatasource.services.moviesapi.response

import com.google.gson.annotations.SerializedName

data class LatestMoviesResponse(
    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("belongs_to_collection") var belongsToCollection: String? = null,
    @SerializedName("budget") var budget: Int? = null,
    @SerializedName("genres") var genres: ArrayList<Genres>? = null,
    @SerializedName("homepage") var homepage: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("imdb_id") var imdbId: String? = null,
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("popularity") var popularity: Int? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("production_companies") var productionCompanies: ArrayList<ProductionCompanies?>? = null,
    @SerializedName("production_countries") var productionCountries: ArrayList<String?>? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("revenue") var revenue: Int? = null,
    @SerializedName("runtime") var runtime: Int? = null,
    @SerializedName("spoken_languages") var spokenLanguages: ArrayList<SpokenLanguages?>? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("tagline") var tagline: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("video") var video: Boolean? = null,
    @SerializedName("vote_average") var voteAverage: Int? = null,
    @SerializedName("vote_count") var voteCount: Int? = null,
) {
    data class Genres(
        @SerializedName("id") var id: Int? = null,
        @SerializedName("name") var name: String? = null,
    )

    data class ProductionCompanies(
        @SerializedName("id") val id: Int?,
        @SerializedName("logo_path") val logoPath: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("origin_country") val originCountry: String?,
    )

    data class SpokenLanguages(
        @SerializedName("english_name") val englishName: String?,
        @SerializedName("iso_639_1") val iso: String?,
        @SerializedName("name") val name: String?,
    )
}

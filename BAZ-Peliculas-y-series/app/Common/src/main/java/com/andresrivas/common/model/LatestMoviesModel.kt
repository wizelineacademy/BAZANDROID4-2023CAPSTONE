package com.andresrivas.bazpeliculasyseries.domain.model

data class LatestMoviesModel(
    var adult: Boolean = false,
    var backdropPath: String = "",
    var belongsToCollection: String = "",
    var budget: Int = 0,
    var genres: List<Genres> = arrayListOf(),
    var homepage: String = "",
    var id: Int = 0,
    var imdbId: String = "",
    var originalLanguage: String = "",
    var originalTitle: String = "",
    var overview: String = "",
    var popularity: Int = 0,
    var posterPath: String = "",
    var productionCompanies: List<ProductionCompanies> = arrayListOf(),
    var productionCountries: List<String> = arrayListOf(),
    var releaseDate: String = "",
    var revenue: Int = 0,
    var runtime: Int = 0,
    var spokenLanguages: List<SpokenLanguages> = arrayListOf(),
    var status: String,
    var tagline: String = "",
    var title: String = "",
    var video: Boolean = false,
    var voteAverage: Int = 0,
    var voteCount: Int = 0,

) {
    data class Genres(
        var id: Int = 0,
        var name: String = "",
    )

    data class ProductionCompanies(
        val id: Int = 0,
        val logoPath: String = "",
        val name: String = "",
        val originCountry: String = "",
    )

    data class SpokenLanguages(
        val englishName: String = "",
        val iso: String = "",
        val name: String = "",
    )
}

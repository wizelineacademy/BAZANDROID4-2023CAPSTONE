package mx.jramon.subias.dbmovieproyect.movies.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvSerieList(
    @SerializedName("pages") @Expose val pages:Int,
    @SerializedName("results") @Expose val listSeries:List<TvSerieEntity>,
    @SerializedName("total_results") @Expose val totalResults:Int,
    @SerializedName("total_pages") @Expose val totalPages:Int
)

data class TvSerieEntity(
    @SerializedName("poster_path") @Expose val posterPath:String?,
    @SerializedName("popularity") @Expose val popularity:Double,
    @SerializedName("id") @Expose val id:Int,
    @SerializedName("backdrop_path") @Expose val backdropPath:String?,
    @SerializedName("vote_average") @Expose val voteAverage:Double,
    @SerializedName("overview") @Expose val overview:String,
    @SerializedName("first_air_date") @Expose val firstAirDate:String,
    @SerializedName("origin_country") @Expose val originCountry:List<String>,
    @SerializedName("genre_ids") @Expose val listIdGenre:List<Int>,
    @SerializedName("original_language") @Expose val originalLanguage:String,
    @SerializedName("vote_count") @Expose val voteCount:Int,
    @SerializedName("name") @Expose val name:String,
    @SerializedName("original_name") @Expose val originalName:String
)
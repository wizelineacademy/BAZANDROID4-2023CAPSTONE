package mx.jramon.subias.dbmovieproyect.movies.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("pages") @Expose val pages:Int,
    @SerializedName("results") @Expose val results:List<MovieEntity>,
    @SerializedName("total_results") @Expose val totalResults:Int,
    @SerializedName("total_pages") @Expose val totalPages:Int
)

data class MovieEntity(
    @SerializedName("poster_path") @Expose val posterPath:String?,
    @SerializedName("adult") @Expose val adult:Boolean,
    @SerializedName("overview") @Expose val overview:String,
    @SerializedName("release_date") @Expose val releaseDate:String,
    @SerializedName("genre_ids") @Expose val listIdGenre:List<Int>,
    @SerializedName("id") @Expose val id:Int,
    @SerializedName("original_title") @Expose val originalTitle:String,
    @SerializedName("original_language") @Expose val language:String,
    @SerializedName("title") @Expose val title:String,
    @SerializedName("backdrop_path") @Expose val backdropPaht:String?,
    @SerializedName("popularity") @Expose val popularity:Double,
    @SerializedName("vote_count") @Expose val voteCount:Int,
    @SerializedName("video") @Expose val video:Boolean,
    @SerializedName("vote_average") @Expose val voteAverage:Double
)
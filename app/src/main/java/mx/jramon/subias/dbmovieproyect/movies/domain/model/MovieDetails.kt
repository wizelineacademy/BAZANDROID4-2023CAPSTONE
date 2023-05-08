package mx.jramon.subias.dbmovieproyect.movies.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Objects

data class MovieDetails(
    @SerializedName("adult") @Expose val adult:Boolean,
    @SerializedName("backdrop_path") @Expose val backdropPath:String?,
    @SerializedName("belongs_to_collection") @Expose val belongsToCollection:Objects?,
    @SerializedName("budget") @Expose val budget:Int,
    @SerializedName("genres") @Expose val genres: List<Genres>,
    @SerializedName("homepage") @Expose val homepage:String?,
    @SerializedName("id") @Expose val id:Int,
    @SerializedName("imdb_id") @Expose val imdb_id:String?,
    @SerializedName("original_language") @Expose val original_language:String,
    @SerializedName("original_title") @Expose val original_title:String,
    @SerializedName("overview") @Expose val overview:String?,
    @SerializedName("popularity") @Expose val popularity:Double,
    @SerializedName("poster_path") @Expose val poster_path:String?,
    @SerializedName("production_companies") @Expose val production_companies:List<CompaniesProduction>,
    @SerializedName("production_countries") @Expose val production_countries:List<CountriesProduction>,
    @SerializedName("release_date") @Expose val release_date:String,
    @SerializedName("revenue") @Expose val revenue:Int,
    @SerializedName("runtime") @Expose val runtime:Int?,
    @SerializedName("spoken_languages") @Expose val spoken_languages:List<SpokenLanguages>,
    @SerializedName("status") @Expose val status:String,
    @SerializedName("tagline") @Expose val tagline:String?,
    @SerializedName("title") @Expose val title:String,
    @SerializedName("video") @Expose val video:Boolean,
    @SerializedName("vote_average") @Expose val vote_average:Float,
    @SerializedName("vote_count") @Expose val vote_count:Int
)

data class Genres(
    @SerializedName("id") @Expose val id:Int,
    @SerializedName("name") @Expose val name:String,
)

data class CompaniesProduction(
    @SerializedName("id") @Expose val id:Int,
    @SerializedName("name") @Expose val name:String,
    @SerializedName("logo_path") @Expose val logoPath:String?,
    @SerializedName("origin_country") @Expose val originCountry:String,
)

data class CountriesProduction(
    @SerializedName("id") @Expose val id:String,
    @SerializedName("iso_3166_1") @Expose val iso:String,
)

data class SpokenLanguages(
    @SerializedName("id") @Expose val id:String,
    @SerializedName("iso_3166_1") @Expose val iso:String,
)

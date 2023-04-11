package mx.jramon.subias.dbmovieproyect.movies.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Objects

data class TvSerieDetails(
    @SerializedName("backdrop_path") @Expose val backdropPath:String?,
    @SerializedName("created_by") @Expose val createBy:List<CreateBy>,
    @SerializedName("episode_run_time") @Expose val episodeRunTime:List<Int>,
    @SerializedName("first_air_date") @Expose val firstAirDate:String,
    @SerializedName("genres") @Expose val genres:List<Genres>,
    @SerializedName("homepage") @Expose val homepage:String,
    @SerializedName("id") @Expose val id:Int,
    @SerializedName("in_production") @Expose val inProduction:Boolean,
    @SerializedName("languages") @Expose val languages:List<String>,
    @SerializedName("last_air_date") @Expose val lastAirDate:String,
    @SerializedName("last_episode_to_air") @Expose val lastEpisodeToAir: LastEpisodeAir,
    @SerializedName("name") @Expose val name:String,
    @SerializedName("next_episode_to_air") @Expose val nextEpisodeToAir:Objects?,
    @SerializedName("networks") @Expose val networks:List<Networks>,
    @SerializedName("number_of_episodes") @Expose val numberEpisodes:Int,
    @SerializedName("number_of_seasons") @Expose val numberSeason:Int,
    @SerializedName("origin_country") @Expose val originCountry:List<String>,
    @SerializedName("original_language") @Expose val originLanguages:String,
    @SerializedName("original_name") @Expose val originalName:String,
    @SerializedName("overview") @Expose val overview:String,
    @SerializedName("popularity") @Expose val popularity:Double,
    @SerializedName("poster_path") @Expose val posterPath:String?,
    @SerializedName("production_companies") @Expose val productionCompanies:List<CompaniesProduction>,
    @SerializedName("production_countries") @Expose val productionCountries:List<CountriesProduction>,
    @SerializedName("seasons") @Expose val seasons:List<Seasons>,
    @SerializedName("spoken_languages") @Expose val spokenLanguages:List<SpokenLanguages>,
    @SerializedName("status") @Expose val status:String,
    @SerializedName("tagline") @Expose val tagline:String,
    @SerializedName("type") @Expose val type:String,
    @SerializedName("vote_average") @Expose val voteAverage:Double,
    @SerializedName("vote_count") @Expose val voteCount:Int
)

data class CreateBy(
    @SerializedName("id") @Expose val id:Int,
    @SerializedName("credit_id") @Expose val creditId:String,
    @SerializedName("name") @Expose val name:String,
    @SerializedName("gender") @Expose val gender:Int,
    @SerializedName("profile_path") @Expose val profilePath:String?,
)

data class LastEpisodeAir(
    @SerializedName("id") @Expose val id:Int,
    @SerializedName("air_date") @Expose val airDate:String,
    @SerializedName("episode_number") @Expose val episodeNumber:Int,
    @SerializedName("name") @Expose val name:String,
    @SerializedName("overview") @Expose val overview: String,
    @SerializedName("production_code") @Expose val productionCode:String,
    @SerializedName("season_number") @Expose val seasonNumber:Int,
    @SerializedName("still_path") @Expose val stillPath:String?,
    @SerializedName("vote_average") @Expose val voteAverage: Double,
    @SerializedName("vote_count") @Expose val voteCount: Int,
)

data class Networks(
    @SerializedName("id") @Expose val id:Int,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("logo_path") @Expose val logoPath:String?,
    @SerializedName("origin_country") @Expose val originCountry: String,
)

data class Seasons(
    @SerializedName("id") @Expose val id:Int,
    @SerializedName("air_date") @Expose val airDate: String,
    @SerializedName("episode_count") @Expose val episodeCount:Int,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("overview") @Expose val overview: String,
    @SerializedName("poster_path") @Expose val posterPath: String?,
    @SerializedName("season_number") @Expose val seasonNumber: Int,

)
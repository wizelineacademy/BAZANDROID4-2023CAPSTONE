package mx.jossprogramming.jlmovieswizel.data.models

import com.google.gson.annotations.SerializedName
import mx.jossprogramming.jlmovieswizel.data.database.entitys.MovieEntity
import java.io.Serializable

data class DetailMovies(
    @SerializedName("adult") var adult: Boolean = false,
    @SerializedName("backdrop_path") var backdrop_path: String = "",
    @SerializedName("genre_ids") var genre_ids: List<Int> = emptyList(),
    @SerializedName("id") var id: Int = 0,
    @SerializedName("overview") var overview: String = "",
    @SerializedName("popularity") var popularity: Double = 0.0,
    @SerializedName("poster_path") var poster_path: String = "",
    @SerializedName("release_date") var release_date: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("video") var video: Boolean = false,
    @SerializedName("vote_average") var vote_average: Double = 0.0,
    @SerializedName("vote_count") var vote_count: Int = 0,
    @SerializedName("portada_id") var portada_id:Int = 0,
    @SerializedName("poster_id") var poster_id:Int = 0,
): Serializable

fun DetailMovies.toEntity(): MovieEntity {
    return MovieEntity(this.id,this.title)
}

fun DetailMovies.getGenres() = this.genre_ids


package mx.jossprogramming.remote.models

import com.google.gson.annotations.SerializedName

data class GenresResponse (
    @SerializedName("genres")
    var genres:List<Genre> = emptyList()
)

data class Genre(
    @SerializedName("id")
    var id:Int,
    @SerializedName("name")
    var name:String
)
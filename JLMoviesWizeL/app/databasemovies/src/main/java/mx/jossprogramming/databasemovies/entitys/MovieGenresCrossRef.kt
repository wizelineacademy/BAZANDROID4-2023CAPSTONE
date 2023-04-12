package mx.jossprogramming.databasemovies.entitys

import androidx.room.Entity

@Entity(primaryKeys = ["id","idGenre"])
data class MovieGenresCrossRef(
    val id:Int,
    val idGenre: Int
)
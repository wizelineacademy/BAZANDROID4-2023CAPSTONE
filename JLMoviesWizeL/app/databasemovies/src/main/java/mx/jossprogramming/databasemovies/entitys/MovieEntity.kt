package mx.jossprogramming.databasemovies.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val id:Int,
    val name:String
)
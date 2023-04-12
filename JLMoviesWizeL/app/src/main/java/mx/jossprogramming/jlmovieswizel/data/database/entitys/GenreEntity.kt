package mx.jossprogramming.jlmovieswizel.data.database.entitys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreEntity(
    @PrimaryKey val idGenre:Int,
    var name:String
)
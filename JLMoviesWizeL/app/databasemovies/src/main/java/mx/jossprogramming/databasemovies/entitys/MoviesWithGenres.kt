package mx.jossprogramming.databasemovies.entitys


import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

//TODO MODELO RELACIONAL PELÍCULAS Y GÉNEROS
data class MoviesWithGenres(
    @Embedded val movie:MovieEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "idGenre",
        associateBy = Junction(MovieGenresCrossRef::class)
    )
    val genres:List<GenreEntity>
)
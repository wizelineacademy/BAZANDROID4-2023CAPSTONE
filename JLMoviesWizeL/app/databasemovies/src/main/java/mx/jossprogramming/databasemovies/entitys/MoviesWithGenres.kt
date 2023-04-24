package mx.jossprogramming.databasemovies.entitys


import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/**
 * Clase que sirve como modelo relacional en room entre las películas y sus gérenerod
 *
 * @author Jose Luis Pino Ucan
 */
data class MoviesWithGenres(
    @Embedded val movie:MovieEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "idGenre",
        associateBy = Junction(MovieGenresCrossRef::class)
    )
    val genres:List<GenreEntity>
)
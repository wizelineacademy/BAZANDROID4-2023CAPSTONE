package mx.jramon.subias.dbmovieproyect.movies.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import mx.jramon.subias.dbmovieproyect.movies.domain.model.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieEntity")
    fun getMovies(): Single<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieEntity(moviesList: List<MovieEntity>)
}
package com.jecruzv.capstonewl.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jecruzv.capstonewl.data.database.entities.PopularMoviesDB
import com.jecruzv.capstonewl.util.Annotations

@Annotations("Entregable 2")
@Dao
interface MoviesDao {
    @Query("SELECT * FROM popular_movies")
    fun getPopularMovies(): LiveData<List<PopularMoviesDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(movies: List<PopularMoviesDB>)
}
package com.edith.movies.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edith.movies.core.data.database.entity.MovieEntity

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( movieEntity: List<MovieEntity>)

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()
}
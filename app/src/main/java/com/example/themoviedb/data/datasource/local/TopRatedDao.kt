package com.example.themoviedb.data.datasource.local

import androidx.room.Dao
import androidx.room.Query
import com.example.themoviedb.base.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface TopRatedDao: BaseDao<TopRatedLocal> {
    @Query("SELECT * FROM TopRatedMovies")
    fun observeAll(): Flow<List<TopRatedLocal>>

    @Query("SELECT * FROM TopRatedMovies WHERE id = :movieId")
    fun observeById(movieId: String): Flow<TopRatedLocal>

    @Query("SELECT * FROM TopRatedMovies")
    suspend fun getAll(): List<TopRatedLocal>

    @Query("SELECT * FROM TopRatedMovies WHERE id = :movieId")
    suspend fun getById(movieId: String): TopRatedLocal

    @Query("DELETE FROM TopRatedMovies")
    suspend fun deleteALl()
}
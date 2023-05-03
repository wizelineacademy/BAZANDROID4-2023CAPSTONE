package com.example.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.local.entity.TopRatedLocal
import com.example.tools.BaseDao
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
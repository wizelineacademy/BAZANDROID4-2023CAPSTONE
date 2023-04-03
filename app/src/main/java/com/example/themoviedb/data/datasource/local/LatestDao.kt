package com.example.themoviedb.data.datasource.local

import androidx.room.Dao
import androidx.room.Query
import com.example.themoviedb.base.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface LatestDao: BaseDao<LatestLocal> {
    @Query("SELECT * FROM LatestMovies")
    fun observeAll(): Flow<List<LatestLocal>>

    @Query("SELECT * FROM LatestMovies WHERE id = :movieId")
    fun observeById(movieId: String): Flow<LatestLocal>

    @Query("SELECT * FROM LatestMovies")
    suspend fun getAll(): List<LatestLocal>

    @Query("SELECT * FROM LatestMovies WHERE id = :movieId")
    suspend fun getById(movieId: String): LatestLocal

    @Query("DELETE FROM LatestMovies")
    suspend fun deleteALl()
}
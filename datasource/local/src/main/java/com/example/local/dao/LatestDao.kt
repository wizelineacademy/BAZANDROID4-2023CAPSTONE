package com.example.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.local.entity.LatestLocal
import com.example.tools.BaseDao
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
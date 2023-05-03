package com.example.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.local.entity.NowPlayingLocal
import com.example.local.entity.NowPlayingLocalWithGenres
import com.example.tools.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface NowPlayingDao: BaseDao<NowPlayingLocal> {

    @Transaction
    @Query("SELECT * FROM NowPlayingMovies")
    fun observeAll(): Flow<List<NowPlayingLocalWithGenres>>

    @Query("SELECT * FROM NowPlayingMovies WHERE id = :movieId")
    fun observeById(movieId: String): Flow<NowPlayingLocal>

    @Query("SELECT * FROM NowPlayingMovies")
    suspend fun getAll(): List<NowPlayingLocal>

    @Query("SELECT * FROM NowPlayingMovies WHERE id = :movieId")
    suspend fun getById(movieId: String): NowPlayingLocal

    @Query("DELETE FROM NowPlayingMovies")
    suspend fun deleteALl()
}
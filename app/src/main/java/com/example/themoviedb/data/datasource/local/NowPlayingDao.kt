package com.example.themoviedb.data.datasource.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.themoviedb.base.BaseDao
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